package model.entities;

import java.sql.Timestamp;
import model.enums.EstadoCanje;

public class CanjeBeneficios {
    private int id;
    private int clubMascotasId;
    private int beneficioId;
    private Timestamp fechaCanje;
    private int puntosCanjeados;
    private EstadoCanje estado;
    private Integer facturaId;

    public CanjeBeneficios(int pId, int pClubMascotasId, int pBeneficioId, Timestamp pFechaCanje, int pPuntosCanjeados, EstadoCanje pEstado, Integer pFacturaId) {
        id = pId;
        clubMascotasId = pClubMascotasId;
        beneficioId = pBeneficioId;
        fechaCanje = pFechaCanje;
        puntosCanjeados = pPuntosCanjeados;
        estado = pEstado;
        facturaId = pFacturaId;
    }
    
    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getClubMascotasId() { return clubMascotasId; }
    public void setClubMascotasId(int pClubMascotasId) { clubMascotasId = pClubMascotasId; }
    public int getBeneficioId() { return beneficioId; }
    public void setBeneficioId(int pBeneficioId) { beneficioId = pBeneficioId; }
    public Timestamp getFechaCanje() { return fechaCanje; }
    public void setFechaCanje(Timestamp pFechaCanje) { fechaCanje = pFechaCanje; }
    public int getPuntosCanjeados() { return puntosCanjeados; }
    public void setPuntosCanjeados(int pPuntosCanjeados) { puntosCanjeados = pPuntosCanjeados; }
    public EstadoCanje getEstado() { return estado; }
    public void setEstado(EstadoCanje pEstado) { estado = pEstado; }
    public Integer getFacturaId() { return facturaId; }
    public void setFacturaId(Integer pFacturaId) { facturaId = pFacturaId; }

    @Override
    public String toString() {
        return "CanjeBeneficios: id=" + id + ", "
                + "\nbeneficioId=" + beneficioId + ", "
                + "\npuntosCanjeados=" + puntosCanjeados;
    }
}