package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import model.entities.Dueño;
import model.entities.Factura;
import model.entities.Inventario;
import model.entities.ItemFactura;
import model.entities.MovimientoInventario;
import model.entities.Servicio;
import model.enums.EstadoFactura;
import model.enums.TipoItemFactura;
import model.enums.TipoMovimientoInventario;
import repository.FacturaDAO;
import repository.InventarioDAO;
import repository.ItemFacturaDAO;
import repository.MovimientoInventarioDAO;
import repository.ServicioDAO;

public class FacturacionController {

    private FacturaDAO facturaDAO;
    private ItemFacturaDAO itemFacturaDAO;
    private PacienteController pacienteController;
    private InventarioDAO inventarioDAO;
    private MovimientoInventarioDAO movimientoInventarioDAO;
    private ServicioDAO servicioDAO;
    
    private static final double IMPUESTO_IVA = 0.19;

    public FacturacionController() {
        this.facturaDAO = new FacturaDAO();
        this.itemFacturaDAO = new ItemFacturaDAO();
        this.pacienteController = new PacienteController();
        this.inventarioDAO = new InventarioDAO();
        this.movimientoInventarioDAO = new MovimientoInventarioDAO();
        this.servicioDAO = new ServicioDAO();
    }

    public void crearFacturaYGenerarTxt(Factura pFactura, List<ItemFactura> pItems) {
        facturaDAO.agregar(pFactura);
        
        if (pFactura.getId() == 0) {
            System.out.println("Error: No se pudo guardar la factura en la base de datos.");
            return;
        }

        for (ItemFactura item : pItems) {
            item.setFacturaId(pFactura.getId());
            itemFacturaDAO.agregar(item);

            if (item.getTipoItem() == TipoItemFactura.PRODUCTO) {
                String resultadoStock = this.reducirStockPorVenta(item.getProductoId(), item.getCantidad(), pFactura.getId());
                
                if (!resultadoStock.equals("Exito")) {
                    System.out.println("ADVERTENCIA: " + resultadoStock);
                }
            }
        }
        
        generarFacturaTxt(pFactura, pItems);
    }

    private String reducirStockPorVenta(int pIdProducto, int pCantidad, int pIdFactura) {
        
        Inventario producto = inventarioDAO.leerPorId(pIdProducto);
        
        if (producto == null) {
            return "Error: El producto con ID " + pIdProducto + " no existe.";
        }

        if (producto.getCantidadStock() < pCantidad) {
            return "Error: Stock insuficiente para el producto " + producto.getNombreProducto();
        }
        
        int stockAnterior = producto.getCantidadStock();
        producto.setCantidadStock(stockAnterior - pCantidad);
        inventarioDAO.actualizar(producto);
        
        String motivo = "Venta en Factura ID: " + pIdFactura;
        MovimientoInventario movimiento = new MovimientoInventario(pIdProducto, TipoMovimientoInventario.SALIDA, pCantidad * -1, stockAnterior, producto.getCantidadStock(), motivo, null, null, "sistema", new Timestamp(System.currentTimeMillis()));
        movimientoInventarioDAO.agregar(movimiento);

        return "Exito";
    }

    private void generarFacturaTxt(Factura pFactura, List<ItemFactura> pItems) {
        Dueño dueño = pacienteController.buscarDueñoPorDocumento(pFactura.getNumeroFactura().split("-")[1]);
        String nombreArchivo = "facturas/Factura_" + pFactura.getNumeroFactura() + ".txt";

        try {
            File directorio = new File("facturas");
            if (!directorio.exists()) { directorio.mkdir(); }

            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
            
            writer.write("========================================================\n");
            writer.write("          VETERINARIA HAPPY FEET - FACTURA\n");
            writer.write("========================================================\n");
            writer.write("Numero Factura: " + pFactura.getNumeroFactura() + "\n");
            writer.write("Fecha de Emision: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(pFactura.getFechaEmision()) + "\n");
            writer.write("--------------------------------------------------------\n");
            writer.write("DATOS DEL CLIENTE\n");
            writer.write("Nombre: " + dueño.getNombre() + "\n");
            writer.write("Documento: " + dueño.getNumeroDocumento() + "\n");
            writer.write("Direccion: " + dueño.getDireccion() + "\n");
            writer.write("--------------------------------------------------------\n");
            writer.write("DESGLOSE DE ITEMS\n");
            writer.write(String.format("%-25s %5s %10s %10s\n", "Descripcion", "Cant", "Vlr. Unit", "Subtotal"));
            writer.write("--------------------------------------------------------\n");

            for (ItemFactura item : pItems) {
                String descripcion = item.getServicioDescripcion();
                writer.write(String.format("%-25s %5d %10.2f %10.2f\n", descripcion, item.getCantidad(), item.getPrecioUnitario(), item.getSubtotal()));
            }

            writer.write("--------------------------------------------------------\n");
            writer.write(String.format("%42s %10.2f\n", "Subtotal:", pFactura.getSubtotal()));
            writer.write(String.format("%42s %10.2f\n", "Impuestos (" + (IMPUESTO_IVA * 100) + "%):", pFactura.getImpuesto()));
            writer.write("========================================================\n");
            writer.write(String.format("%42s %10.2f\n", "TOTAL A PAGAR:", pFactura.getTotal()));
            writer.write("========================================================\n");
            
            writer.close();
            System.out.println("Factura generada con exito en el archivo: " + nombreArchivo);

        } catch (IOException e) {
            System.out.println("Ocurrio un error al escribir el archivo de la factura.");
            e.printStackTrace();
        }
    }

    public List<String> generarReporteServiciosMasSolicitados() {
        List<ItemFactura> items = new ArrayList<>();
        facturaDAO.listar().forEach(f -> items.addAll(itemFacturaDAO.listarPorFacturaId(f.getId())));
        
        Map<Integer, Long> conteo = items.stream()
            .filter(item -> item.getTipoItem() == model.enums.TipoItemFactura.SERVICIO && item.getServicioId() != null)
            .collect(Collectors.groupingBy(ItemFactura::getServicioId, Collectors.counting()));
            
        List<String> reporte = new ArrayList<>();
        
        conteo.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEach(entry -> {
                Servicio s = servicioDAO.leerPorId(entry.getKey());
                if (s != null) {
                    reporte.add("Servicio: " + s.getNombre() + " | Solicitado: " + entry.getValue() + " veces");
                }
            });
        return reporte;
    }
    
    public String generarReporteFacturacion(Timestamp pInicio, Timestamp pFin) {
        
        List<Factura> facturasEnPeriodo = facturaDAO.listar().stream()
            .filter(f -> f.getFechaEmision().after(pInicio) && f.getFechaEmision().before(pFin))
            .collect(Collectors.toList());
        
        double totalFacturado = facturasEnPeriodo.stream().mapToDouble(Factura::getTotal).sum();
        int numeroFacturas = facturasEnPeriodo.size();
        
        return "Reporte de Facturacion:\n" + "Periodo: " + pInicio + " a " + pFin + "\n" + "Total de Facturas: " + numeroFacturas + "\n" + "Monto Total Facturado: " + String.format("%.2f", totalFacturado);
    }
    
    public List<Inventario> getProductosConStockBajo() {
        return inventarioDAO.listar().stream().filter(p -> p.getCantidadStock() < p.getStockMinimo()).collect(Collectors.toList());
    }

    public Dueño buscarDueñoPorDocumento(String pDoc) {
        return pacienteController.buscarDueñoPorDocumento(pDoc);
    }
    
    public List<Servicio> getServiciosActivos() {
        return servicioDAO.listar().stream().filter(Servicio::getActivo).collect(Collectors.toList());
    }

    public List<Inventario> getProductosActivos() {
        return inventarioDAO.listar().stream().filter(Inventario::getActivo).collect(Collectors.toList());
    }

    public Inventario buscarProductoPorId(int pId) {
        return inventarioDAO.leerPorId(pId);
    }
    
    public void agregarServicio(Servicio pServicio) {
        servicioDAO.agregar(pServicio);
    }

    public List<Servicio> listarServicios() {
        return servicioDAO.listar();
    }
    
    public Servicio buscarServicioPorId(int pId) {
        return servicioDAO.leerPorId(pId);
    }
    
    public void actualizarServicio(Servicio pServicio) {
        servicioDAO.actualizar(pServicio);
    }

    public void eliminarServicio(int pId) {
        servicioDAO.eliminar(pId);
    }
}