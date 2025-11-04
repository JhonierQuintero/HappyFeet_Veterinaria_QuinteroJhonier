package model.entities;

import java.sql.Timestamp;
import model.enums.TipoTransaccionPuntos;

public class TransaccionPuntos {
    private int id;
    private int clubMascotasId;
    private Integer facturaId;
    private int puntos;
    private TipoTransaccionPuntos tipo;
    private Timestamp fecha;
    private String descripcion;
    private int saldoAnterior;
    private int saldoNuevo;

    public TransaccionPuntos(int pId, int pClubMascotasId, Integer pFacturaId, int pPuntos, TipoTransaccionPuntos pTipo, Timestamp pFecha, String pDescripcion, int pSaldoAnterior, int pSaldoNuevo) {
        id = pId;
        clubMascotasId = pClubMascotasId;
        facturaId = pFacturaId;
        puntos = pPuntos;
        tipo = pTipo;
        fecha = pFecha;
        descripcion = pDescripcion;
        saldoAnterior = pSaldoAnterior;
        saldoNuevo = pSaldoNuevo;
    }

    public TransaccionPuntos(int pClubMascotasId, Integer pFacturaId, int pPuntos, TipoTransaccionPuntos pTipo, Timestamp pFecha, String pDescripcion, int pSaldoAnterior, int pSaldoNuevo) {
        clubMascotasId = pClubMascotasId;
        facturaId = pFacturaId;
        puntos = pPuntos;
        tipo = pTipo;
        fecha = pFecha;
        descripcion = pDescripcion;
        saldoAnterior = pSaldoAnterior;
        saldoNuevo = pSaldoNuevo;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getClubMascotasId() { return clubMascotasId; }
    public void setClubMascotasId(int pClubMascotasId) { clubMascotasId = pClubMascotasId; }
    public Integer getFacturaId() { return facturaId; }
    public void setFacturaId(Integer pFacturaId) { facturaId = pFacturaId; }
    public int getPuntos() { return puntos; }
    public void setPuntos(int pPuntos) { puntos = pPuntos; }
    public TipoTransaccionPuntos getTipo() { return tipo; }
    public void setTipo(TipoTransaccionPuntos pTipo) { tipo = pTipo; }
    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp pFecha) { fecha = pFecha; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String pDescripcion) { descripcion = pDescripcion; }
    public int getSaldoAnterior() { return saldoAnterior; }
    public void setSaldoAnterior(int pSaldoAnterior) { saldoAnterior = pSaldoAnterior; }
    public int getSaldoNuevo() { return saldoNuevo; }
    public void setSaldoNuevo(int pSaldoNuevo) { saldoNuevo = pSaldoNuevo; }

    @Override
    public String toString() {
        return "TransaccionPuntos: id=" + id + ", "
                + "\ntipo=" + tipo + ", "
                + "\npuntos=" + puntos;
    }
}