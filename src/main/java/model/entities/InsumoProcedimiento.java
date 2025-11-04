package model.entities;

public class InsumoProcedimiento {
    private int id;
    private int procedimientoId;
    private int productoId;
    private int cantidadUsada;
    private String observaciones;

    public InsumoProcedimiento(int pId, int pProcedimientoId, int pProductoId, int pCantidadUsada, String pObservaciones) {
        id = pId;
        procedimientoId = pProcedimientoId;
        productoId = pProductoId;
        cantidadUsada = pCantidadUsada;
        observaciones = pObservaciones;
    }

    public InsumoProcedimiento(int pProcedimientoId, int pProductoId, int pCantidadUsada, String pObservaciones) {
        procedimientoId = pProcedimientoId;
        productoId = pProductoId;
        cantidadUsada = pCantidadUsada;
        observaciones = pObservaciones;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getProcedimientoId() { return procedimientoId; }
    public void setProcedimientoId(int pProcedimientoId) { procedimientoId = pProcedimientoId; }
    public int getProductoId() { return productoId; }
    public void setProductoId(int pProductoId) { productoId = pProductoId; }
    public int getCantidadUsada() { return cantidadUsada; }
    public void setCantidadUsada(int pCantidadUsada) { cantidadUsada = pCantidadUsada; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String pObservaciones) { observaciones = pObservaciones; }

    @Override
    public String toString() {
        return "InsumoProcedimiento: id=" + id + ", "
                + "\nprocedimientoId=" + procedimientoId + ", "
                + "\nproductoId=" + productoId + ", "
                + "\ncantidadUsada=" + cantidadUsada ;
    }
}