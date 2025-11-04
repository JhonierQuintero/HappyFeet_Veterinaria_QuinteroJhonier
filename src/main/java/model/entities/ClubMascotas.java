package model.entities;

import java.sql.Date;

public class ClubMascotas {
    private int id;
    private int duenoId;
    private int puntosDisponibles;
    private Date fechaInscripcion;
    private boolean activo;

    public ClubMascotas(int pId, int pDuenoId, int pPuntosDisponibles, Date pFechaInscripcion, boolean pActivo) {
        id = pId;
        duenoId = pDuenoId;
        puntosDisponibles = pPuntosDisponibles;
        fechaInscripcion = pFechaInscripcion;
        activo = pActivo;
    }

    public ClubMascotas(int pDuenoId, int pPuntosDisponibles, Date pFechaInscripcion, boolean pActivo) {
        duenoId = pDuenoId;
        puntosDisponibles = pPuntosDisponibles;
        fechaInscripcion = pFechaInscripcion;
        activo = pActivo;
    }
    
    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getDuenoId() { return duenoId; }
    public void setDuenoId(int pDuenoId) { duenoId = pDuenoId; }
    public int getPuntosDisponibles() { return puntosDisponibles; }
    public void setPuntosDisponibles(int pPuntosDisponibles) { puntosDisponibles = pPuntosDisponibles; }
    public Date getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(Date pFechaInscripcion) { fechaInscripcion = pFechaInscripcion; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean pActivo) { activo = pActivo; }

    @Override
    public String toString() {
        return "ClubMascotas: id=" + id + ", "
                + "\nduenoId=" + duenoId + ", "
                + "\npuntosDisponibles=" + puntosDisponibles;
    }
}