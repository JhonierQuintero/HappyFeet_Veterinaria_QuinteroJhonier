package model.entities;

import java.sql.Timestamp;
import model.enums.EstadoFactura;
import model.enums.MetodoPago;

public class Factura {
    private int id;
    private int duenoId;
    private String numeroFactura;
    private Timestamp fechaEmision;
    private double subtotal;
    private double impuesto;
    private double descuento;
    private double total;
    private MetodoPago metodoPago;
    private EstadoFactura estado;
    private String observaciones;

    public Factura(int pId, int pDuenoId, String pNumeroFactura, Timestamp pFechaEmision, double pSubtotal, double pImpuesto, double pDescuento, double pTotal, MetodoPago pMetodoPago, EstadoFactura pEstado, String pObservaciones) {
        id = pId;
        duenoId = pDuenoId;
        numeroFactura = pNumeroFactura;
        fechaEmision = pFechaEmision;
        subtotal = pSubtotal;
        impuesto = pImpuesto;
        descuento = pDescuento;
        total = pTotal;
        metodoPago = pMetodoPago;
        estado = pEstado;
        observaciones = pObservaciones;
    }
    
    public Factura(int pDuenoId, String pNumeroFactura, Timestamp pFechaEmision, double pSubtotal, double pImpuesto, double pDescuento, double pTotal, MetodoPago pMetodoPago, EstadoFactura pEstado, String pObservaciones) {
        duenoId = pDuenoId;
        numeroFactura = pNumeroFactura;
        fechaEmision = pFechaEmision;
        subtotal = pSubtotal;
        impuesto = pImpuesto;
        descuento = pDescuento;
        total = pTotal;
        metodoPago = pMetodoPago;
        estado = pEstado;
        observaciones = pObservaciones;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getDuenoId() { return duenoId; }
    public void setDuenoId(int pDuenoId) { duenoId = pDuenoId; }
    public String getNumeroFactura() { return numeroFactura; }
    public void setNumeroFactura(String pNumeroFactura) { numeroFactura = pNumeroFactura; }
    public Timestamp getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(Timestamp pFechaEmision) { fechaEmision = pFechaEmision; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double pSubtotal) { subtotal = pSubtotal; }
    public double getImpuesto() { return impuesto; }
    public void setImpuesto(double pImpuesto) { impuesto = pImpuesto; }
    public double getDescuento() { return descuento; }
    public void setDescuento(double pDescuento) { descuento = pDescuento; }
    public double getTotal() { return total; }
    public void setTotal(double pTotal) { total = pTotal; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago pMetodoPago) { metodoPago = pMetodoPago; }
    public EstadoFactura getEstado() { return estado; }
    public void setEstado(EstadoFactura pEstado) { estado = pEstado; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String pObservaciones) { observaciones = pObservaciones; }
    
    @Override
    public String toString() {
        return "Factura: id=" + id + ", "
                + "\nnumeroFactura='" + numeroFactura + "', "
                + "\ntotal=" + total + ", "
                + "\nestado=" + estado;
    }
}