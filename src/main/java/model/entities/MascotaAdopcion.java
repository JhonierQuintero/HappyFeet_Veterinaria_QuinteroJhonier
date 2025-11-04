package model.entities;

import java.sql.Date;
import model.enums.EstadoAdopcion;

public class MascotaAdopcion {
    private int id;
    private int mascotaId;
    private Date fechaIngreso;
    private EstadoAdopcion estado;
    private String historia;

    public MascotaAdopcion(int pId, int pMascotaId, Date pFechaIngreso, EstadoAdopcion pEstado, String pHistoria) {
        id = pId;
        mascotaId = pMascotaId;
        fechaIngreso = pFechaIngreso;
        estado = pEstado;
        historia = pHistoria;
    }

    public MascotaAdopcion(int pMascotaId, Date pFechaIngreso, EstadoAdopcion pEstado, String pHistoria) {
        mascotaId = pMascotaId;
        fechaIngreso = pFechaIngreso;
        estado = pEstado;
        historia = pHistoria;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getMascotaId() { return mascotaId; }
    public void setMascotaId(int pMascotaId) { mascotaId = pMascotaId; }
    public Date getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Date pFechaIngreso) { fechaIngreso = pFechaIngreso; }
    public EstadoAdopcion getEstado() { return estado; }
    public void setEstado(EstadoAdopcion pEstado) { estado = pEstado; }
    public String getHistoria() { return historia; }
    public void setHistoria(String pHistoria) { historia = pHistoria; }

    @Override
    public String toString() {
        return "MascotaAdopcion: id=" + id + ", "
                + "\nmascotaId=" + mascotaId + ", "
                + "\nestado=" + estado;
    }
}