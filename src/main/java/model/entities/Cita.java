package model.entities;

import java.sql.Timestamp;

public class Cita {
    private int id;
    private int mascotaId;
    private int veterinarioId;
    private Timestamp fechaHora;
    private String motivo;
    private int estadoId;
    private String observaciones;

    public Cita(int pId, int pMascotaId, int pVeterinarioId, Timestamp pFechaHora, String pMotivo, int pEstadoId, String pObservaciones) {
        id = pId;
        mascotaId = pMascotaId;
        veterinarioId = pVeterinarioId;
        fechaHora = pFechaHora;
        motivo = pMotivo;
        estadoId = pEstadoId;
        observaciones = pObservaciones;
    }

    public Cita(int pMascotaId, int pVeterinarioId, Timestamp pFechaHora, String pMotivo, int pEstadoId, String pObservaciones) {
        mascotaId = pMascotaId;
        veterinarioId = pVeterinarioId;
        fechaHora = pFechaHora;
        motivo = pMotivo;
        estadoId = pEstadoId;
        observaciones = pObservaciones;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getMascotaId() { return mascotaId; }
    public void setMascotaId(int pMascotaId) { mascotaId = pMascotaId; }
    public int getVeterinarioId() { return veterinarioId; }
    public void setVeterinarioId(int pVeterinarioId) { veterinarioId = pVeterinarioId; }
    public Timestamp getFechaHora() { return fechaHora; }
    public void setFechaHora(Timestamp pFechaHora) { fechaHora = pFechaHora; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String pMotivo) { motivo = pMotivo; }
    public int getEstadoId() { return estadoId; }
    public void setEstadoId(int pEstadoId) { estadoId = pEstadoId; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String pObservaciones) { observaciones = pObservaciones; }
    
    @Override
    public String toString() {
        return "Cita: id=" + id + ", "
                + "\nmascotaId=" + mascotaId + ", "
                +"\nveterinarioID=" + veterinarioId+ ", "
                + "\nfechaHora=" + fechaHora + ", "
                + "\nmotivo='" + motivo + "'";
    }
}