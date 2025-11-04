package model.entities;

import model.enums.TipoItemFactura;

public class ItemFactura {
    private int id;
    private int facturaId;
    private TipoItemFactura tipoItem;
    private Integer productoId;
    private Integer servicioId;
    private String servicioDescripcion;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ItemFactura(int pId, int pFacturaId, TipoItemFactura pTipoItem, Integer pProductoId, Integer pServicioId, String pServicioDescripcion, int pCantidad, double pPrecioUnitario, double pSubtotal) {
        id = pId;
        facturaId = pFacturaId;
        tipoItem = pTipoItem;
        productoId = pProductoId;
        servicioId = pServicioId;
        servicioDescripcion = pServicioDescripcion;
        cantidad = pCantidad;
        precioUnitario = pPrecioUnitario;
        subtotal = pSubtotal;
    }
    
    public ItemFactura(int pFacturaId, TipoItemFactura pTipoItem, Integer pProductoId, Integer pServicioId, String pServicioDescripcion, int pCantidad, double pPrecioUnitario, double pSubtotal) {
        facturaId = pFacturaId;
        tipoItem = pTipoItem;
        productoId = pProductoId;
        servicioId = pServicioId;
        servicioDescripcion = pServicioDescripcion;
        cantidad = pCantidad;
        precioUnitario = pPrecioUnitario;
        subtotal = pSubtotal;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getFacturaId() { return facturaId; }
    public void setFacturaId(int pFacturaId) { facturaId = pFacturaId; }
    public TipoItemFactura getTipoItem() { return tipoItem; }
    public void setTipoItem(TipoItemFactura pTipoItem) { tipoItem = pTipoItem; }
    public Integer getProductoId() { return productoId; }
    public void setProductoId(Integer pProductoId) { productoId = pProductoId; }
    public Integer getServicioId() { return servicioId; }
    public void setServicioId(Integer pServicioId) { servicioId = pServicioId; }
    public String getServicioDescripcion() { return servicioDescripcion; }
    public void setServicioDescripcion(String pServicioDescripcion) { servicioDescripcion = pServicioDescripcion; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int pCantidad) { cantidad = pCantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double pPrecioUnitario) { precioUnitario = pPrecioUnitario; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double pSubtotal) { subtotal = pSubtotal; }

    @Override
    public String toString() {
        return "ItemFactura: id=" + id + ", "
                + "\nfacturaId=" + facturaId + ", "
                + "\ntipoItem=" + tipoItem + ", "
                + "\nsubtotal=" + subtotal ;
    }
}