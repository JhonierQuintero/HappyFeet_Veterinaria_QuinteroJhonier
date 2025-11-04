package controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import model.entities.Inventario;
import model.entities.MovimientoInventario;
import model.entities.ProductoTipo;
import model.entities.Proveedor;
import model.enums.TipoMovimientoInventario;
import repository.InventarioDAO;
import repository.MovimientoInventarioDAO;
import repository.ProductoTipoDAO;
import repository.ProveedorDAO;

public class InventarioController {

    private InventarioDAO inventarioDAO;
    private ProveedorDAO proveedorDAO;
    private ProductoTipoDAO productoTipoDAO;
    private MovimientoInventarioDAO movimientoInventarioDAO;

    public InventarioController() {
        this.inventarioDAO = new InventarioDAO();
        this.proveedorDAO = new ProveedorDAO();
        this.productoTipoDAO = new ProductoTipoDAO();
        this.movimientoInventarioDAO = new MovimientoInventarioDAO();
    }

    public void agregarProducto(Inventario pProducto) {
        inventarioDAO.agregar(pProducto);
        registrarMovimiento(pProducto, pProducto.getCantidadStock(), 0, "Registro inicial de producto");
    }

    public List<Inventario> listarProductos() {
        return inventarioDAO.listar();
    }
    
    public Inventario buscarProductoPorId(int pId) {
        return inventarioDAO.leerPorId(pId);
    }
    
    public void actualizarProducto(Inventario pProducto) {
        inventarioDAO.actualizar(pProducto);
    }

    public String ajustarStock(int pIdProducto, int pNuevaCantidad, String pMotivo) {
        Inventario producto = inventarioDAO.leerPorId(pIdProducto);
        
        if (producto == null) {
            return "Error: Producto no encontrado.";
        }
        
        int stockAnterior = producto.getCantidadStock();
        producto.setCantidadStock(pNuevaCantidad);
        inventarioDAO.actualizar(producto);
        
        registrarMovimiento(producto, pNuevaCantidad - stockAnterior, stockAnterior, pMotivo);
        return "Stock ajustado con exito.";
    }

    public List<Inventario> obtenerProductosConStockBajo() {
        return inventarioDAO.listar().stream()
                .filter(p -> p.getCantidadStock() < p.getStockMinimo())
                .collect(Collectors.toList());
    }

    public List<Inventario> obtenerProductosProximosAVencer(int pDias) {
        LocalDate fechaLimite = LocalDate.now().plusDays(pDias);
        Date fechaLimiteSql = Date.valueOf(fechaLimite);
        
        return inventarioDAO.listar().stream()
                .filter(p -> p.getFechaVencimiento() != null && p.getFechaVencimiento().before(fechaLimiteSql))
                .collect(Collectors.toList());
    }

    public void agregarProveedor(Proveedor pProveedor) {
        proveedorDAO.agregar(pProveedor);
    }

    public List<Proveedor> listarProveedores() {
        return proveedorDAO.listar();
    }
    
    public Proveedor buscarProveedorPorId(int pId) {
        return proveedorDAO.leerPorId(pId);
    }
    
    public void actualizarProveedor(Proveedor pProveedor) {
        proveedorDAO.actualizar(pProveedor);
    }
    
    public void eliminarProveedor(int pId) {
        proveedorDAO.eliminar(pId);
    }
    
    public List<ProductoTipo> listarTiposDeProducto() {
        return productoTipoDAO.listar();
    }
    
    private void registrarMovimiento(Inventario pProducto, int pCantidad, int pStockAnterior, String pMotivo) {
        TipoMovimientoInventario tipo;
        
        if (pMotivo.contains("inicial")) {
            tipo = TipoMovimientoInventario.ENTRADA;
        } else if (pCantidad > 0) {
            tipo = TipoMovimientoInventario.ENTRADA;
        } else {
            tipo = TipoMovimientoInventario.SALIDA;
        }

        MovimientoInventario movimiento = new MovimientoInventario(
            pProducto.getId(), tipo, pCantidad, pStockAnterior, pProducto.getCantidadStock(), 
            pMotivo, null, null, "sistema", new Timestamp(System.currentTimeMillis())
        );
        
        movimientoInventarioDAO.agregar(movimiento);
    }

    public String reducirStockPorPrescripcion(int pIdProducto, int pCantidad, int pIdConsulta) {
        
        Inventario producto = inventarioDAO.leerPorId(pIdProducto);
        
        if (producto == null) {
            return "Error: El producto seleccionado no existe.";
        }

        if (producto.getFechaVencimiento() != null && producto.getFechaVencimiento().before(new java.util.Date())) {
            return "Error: El producto esta vencido y no puede ser utilizado.";
        }

        if (producto.getCantidadStock() < pCantidad) {
            return "Error: Stock insuficiente. Solo hay " + producto.getCantidadStock() + " unidades disponibles.";
        }

        int stockAnterior = producto.getCantidadStock();
        producto.setCantidadStock(stockAnterior - pCantidad);
        inventarioDAO.actualizar(producto);

        MovimientoInventario movimiento = new MovimientoInventario(
            pIdProducto, TipoMovimientoInventario.SALIDA, pCantidad * -1, stockAnterior, producto.getCantidadStock(), 
            "Prescripcion en consulta ID: " + pIdConsulta, pIdConsulta, null, "sistema", new Timestamp(System.currentTimeMillis())
        );
        movimientoInventarioDAO.agregar(movimiento);

        return "Exito";
    }
    
}
