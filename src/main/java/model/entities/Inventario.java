package model.entities;

import java.sql.Date;

public class Inventario {
    private int id;
    private String nombreProducto;
    private int productoTipoId;
    private String descripcion;
    private String fabricante;
    private Integer proveedorId;
    private String lote;
    private int cantidadStock;
    private int stockMinimo;
    private String unidadMedida;
    private Date fechaVencimiento;
    private double precioCompra;
    private double precioVenta;
    private boolean requiereReceta;
    private boolean activo;

    public Inventario(int pId, String pNombreProducto, int pProductoTipoId, String pDescripcion, String pFabricante, Integer pProveedorId, String pLote, int pCantidadStock, int pStockMinimo, String pUnidadMedida, Date pFechaVencimiento, double pPrecioCompra, double pPrecioVenta, boolean pRequiereReceta, boolean pActivo) {
        id = pId;
        nombreProducto = pNombreProducto;
        productoTipoId = pProductoTipoId;
        descripcion = pDescripcion;
        fabricante = pFabricante;
        proveedorId = pProveedorId;
        lote = pLote;
        cantidadStock = pCantidadStock;
        stockMinimo = pStockMinimo;
        unidadMedida = pUnidadMedida;
        fechaVencimiento = pFechaVencimiento;
        precioCompra = pPrecioCompra;
        precioVenta = pPrecioVenta;
        requiereReceta = pRequiereReceta;
        activo = pActivo;
    }

    public Inventario(String pNombreProducto, int pProductoTipoId, String pDescripcion, String pFabricante, Integer pProveedorId, String pLote, int pCantidadStock, int pStockMinimo, String pUnidadMedida, Date pFechaVencimiento, double pPrecioCompra, double pPrecioVenta, boolean pRequiereReceta, boolean pActivo) {
        nombreProducto = pNombreProducto;
        productoTipoId = pProductoTipoId;
        descripcion = pDescripcion;
        fabricante = pFabricante;
        proveedorId = pProveedorId;
        lote = pLote;
        cantidadStock = pCantidadStock;
        stockMinimo = pStockMinimo;
        unidadMedida = pUnidadMedida;
        fechaVencimiento = pFechaVencimiento;
        precioCompra = pPrecioCompra;
        precioVenta = pPrecioVenta;
        requiereReceta = pRequiereReceta;
        activo = pActivo;
    }
    
    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String pNombreProducto) { nombreProducto = pNombreProducto; }
    public int getProductoTipoId() { return productoTipoId; }
    public void setProductoTipoId(int pProductoTipoId) { productoTipoId = pProductoTipoId; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String pDescripcion) { descripcion = pDescripcion; }
    public String getFabricante() { return fabricante; }
    public void setFabricante(String pFabricante) { fabricante = pFabricante; }
    public Integer getProveedorId() { return proveedorId; }
    public void setProveedorId(Integer pProveedorId) { proveedorId = pProveedorId; }
    public String getLote() { return lote; }
    public void setLote(String pLote) { lote = pLote; }
    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int pCantidadStock) { cantidadStock = pCantidadStock; }
    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int pStockMinimo) { stockMinimo = pStockMinimo; }
    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String pUnidadMedida) { unidadMedida = pUnidadMedida; }
    public Date getFechaVencimiento() { return fechaVencimiento; }
    public void setFechaVencimiento(Date pFechaVencimiento) { fechaVencimiento = pFechaVencimiento; }
    public double getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(double pPrecioCompra) { precioCompra = pPrecioCompra; }
    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double pPrecioVenta) { precioVenta = pPrecioVenta; }
    public boolean getRequiereReceta() { return requiereReceta; }
    public void setRequiereReceta(boolean pRequiereReceta) { requiereReceta = pRequiereReceta; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean pActivo) { activo = pActivo; }

    @Override
    public String toString() {
        return "Inventario: id=" + id + ", "
                + "\nnombreProducto='" + nombreProducto + "', "
                + "\ncantidadStock=" + cantidadStock ;
    }
}