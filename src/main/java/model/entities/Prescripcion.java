package model.entities;

import java.sql.Timestamp;

public class Prescripcion {
    private int id;
    private Integer consultaId;
    private Integer procedimientoId;
    private int productoId;
    private int cantidad;
    private String dosis;
    private String frecuencia;
    private int duracionDias;
    private String instrucciones;
    private Timestamp fechaPrescripcion;

    public Prescripcion(int pId, Integer pConsultaId, Integer pProcedimientoId, int pProductoId, int pCantidad, String pDosis, String pFrecuencia, int pDuracionDias, String pInstrucciones, Timestamp pFechaPrescripcion) {
        id = pId;
        consultaId = pConsultaId;
        procedimientoId = pProcedimientoId;
        productoId = pProductoId;
        cantidad = pCantidad;
        dosis = pDosis;
        frecuencia = pFrecuencia;
        duracionDias = pDuracionDias;
        instrucciones = pInstrucciones;
        fechaPrescripcion = pFechaPrescripcion;
    }
    
    public Prescripcion(Integer pConsultaId, Integer pProcedimientoId, int pProductoId, int pCantidad, String pDosis, String pFrecuencia, int pDuracionDias, String pInstrucciones, Timestamp pFechaPrescripcion) {
        consultaId = pConsultaId;
        procedimientoId = pProcedimientoId;
        productoId = pProductoId;
        cantidad = pCantidad;
        dosis = pDosis;
        frecuencia = pFrecuencia;
        duracionDias = pDuracionDias;
        instrucciones = pInstrucciones;
        fechaPrescripcion = pFechaPrescripcion;
    }
    
    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public Integer getConsultaId() { return consultaId; }
    public void setConsultaId(Integer pConsultaId) { consultaId = pConsultaId; }
    public Integer getProcedimientoId() { return procedimientoId; }
    public void setProcedimientoId(Integer pProcedimientoId) { procedimientoId = pProcedimientoId; }
    public int getProductoId() { return productoId; }
    public void setProductoId(int pProductoId) { productoId = pProductoId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int pCantidad) { cantidad = pCantidad; }
    public String getDosis() { return dosis; }
    public void setDosis(String pDosis) { dosis = pDosis; }
    public String getFrecuencia() { return frecuencia; }
    public void setFrecuencia(String pFrecuencia) { frecuencia = pFrecuencia; }
    public int getDuracionDias() { return duracionDias; }
    public void setDuracionDias(int pDuracionDias) { duracionDias = pDuracionDias; }
    public String getInstrucciones() { return instrucciones; }
    public void setInstrucciones(String pInstrucciones) { instrucciones = pInstrucciones; }
    public Timestamp getFechaPrescripcion() { return fechaPrescripcion; }
    public void setFechaPrescripcion(Timestamp pFechaPrescripcion) { fechaPrescripcion = pFechaPrescripcion; }

    @Override
    public String toString() {
        return "Prescripcion: id=" + id + ", "
                + "\nproductoId=" + productoId + ", "
                + "\ncantidad=" + cantidad ;
    }
}