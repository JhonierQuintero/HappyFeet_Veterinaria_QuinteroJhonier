package model.entities;

import java.sql.Timestamp;
import model.enums.TipoMovimientoInventario;

public class MovimientoInventario {
    private int id;
    private int productoId;
    private TipoMovimientoInventario tipoMovimiento;
    private int cantidad;
    private int stockAnterior;
    private int stockNuevo;
    private String motivo;
    private Integer referenciaConsultaId;
    private Integer referenciaProcedimientoId;
    private String usuario;
    private Timestamp fechaMovimiento;

    public MovimientoInventario(int pId, int pProductoId, TipoMovimientoInventario pTipoMovimiento, int pCantidad, int pStockAnterior, int pStockNuevo, String pMotivo, Integer pReferenciaConsultaId, Integer pReferenciaProcedimientoId, String pUsuario, Timestamp pFechaMovimiento) {
        id = pId;
        productoId = pProductoId;
        tipoMovimiento = pTipoMovimiento;
        cantidad = pCantidad;
        stockAnterior = pStockAnterior;
        stockNuevo = pStockNuevo;
        motivo = pMotivo;
        referenciaConsultaId = pReferenciaConsultaId;
        referenciaProcedimientoId = pReferenciaProcedimientoId;
        usuario = pUsuario;
        fechaMovimiento = pFechaMovimiento;
    }

    public MovimientoInventario(int pProductoId, TipoMovimientoInventario pTipoMovimiento, int pCantidad, int pStockAnterior, int pStockNuevo, String pMotivo, Integer pReferenciaConsultaId, Integer pReferenciaProcedimientoId, String pUsuario, Timestamp pFechaMovimiento) {
        productoId = pProductoId;
        tipoMovimiento = pTipoMovimiento;
        cantidad = pCantidad;
        stockAnterior = pStockAnterior;
        stockNuevo = pStockNuevo;
        motivo = pMotivo;
        referenciaConsultaId = pReferenciaConsultaId;
        referenciaProcedimientoId = pReferenciaProcedimientoId;
        usuario = pUsuario;
        fechaMovimiento = pFechaMovimiento;
    }
    
    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getProductoId() { return productoId; }
    public void setProductoId(int pProductoId) { productoId = pProductoId; }
    public TipoMovimientoInventario getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(TipoMovimientoInventario pTipoMovimiento) { tipoMovimiento = pTipoMovimiento; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int pCantidad) { cantidad = pCantidad; }
    public int getStockAnterior() { return stockAnterior; }
    public void setStockAnterior(int pStockAnterior) { stockAnterior = pStockAnterior; }
    public int getStockNuevo() { return stockNuevo; }
    public void setStockNuevo(int pStockNuevo) { stockNuevo = pStockNuevo; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String pMotivo) { motivo = pMotivo; }
    public Integer getReferenciaConsultaId() { return referenciaConsultaId; }
    public void setReferenciaConsultaId(Integer pReferenciaConsultaId) { referenciaConsultaId = pReferenciaConsultaId; }
    public Integer getReferenciaProcedimientoId() { return referenciaProcedimientoId; }
    public void setReferenciaProcedimientoId(Integer pReferenciaProcedimientoId) { referenciaProcedimientoId = pReferenciaProcedimientoId; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String pUsuario) { usuario = pUsuario; }
    public Timestamp getFechaMovimiento() { return fechaMovimiento; }
    public void setFechaMovimiento(Timestamp pFechaMovimiento) { fechaMovimiento = pFechaMovimiento; }
    
    @Override
    public String toString() {
        return "MovimientoInventario: id=" + id + ", "
                + "\nproductoId=" + productoId + ", "
                + "\ntipoMovimiento=" + tipoMovimiento;
    }
}