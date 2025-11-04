package model.entities;

import java.sql.Date;
import java.sql.Timestamp;

public class RegistroJornadaVacunacion {
    private int id;
    private int jornadaId;
    private int mascotaId;
    private int duenoId;
    private int vacunaId;
    private Integer veterinarioId;
    private Timestamp fechaHora;
    private String loteVacuna;
    private Date proximaDosis;
    private String observaciones;

    public RegistroJornadaVacunacion(int pId, int pJornadaId, int pMascotaId, int pDuenoId, int pVacunaId, Integer pVeterinarioId, Timestamp pFechaHora, String pLoteVacuna, Date pProximaDosis, String pObservaciones) {
        id = pId;
        jornadaId = pJornadaId;
        mascotaId = pMascotaId;
        duenoId = pDuenoId;
        vacunaId = pVacunaId;
        veterinarioId = pVeterinarioId;
        fechaHora = pFechaHora;
        loteVacuna = pLoteVacuna;
        proximaDosis = pProximaDosis;
        observaciones = pObservaciones;
    }

    public RegistroJornadaVacunacion(int pJornadaId, int pMascotaId, int pDuenoId, int pVacunaId, Integer pVeterinarioId, Timestamp pFechaHora, String pLoteVacuna, Date pProximaDosis, String pObservaciones) {
        jornadaId = pJornadaId;
        mascotaId = pMascotaId;
        duenoId = pDuenoId;
        vacunaId = pVacunaId;
        veterinarioId = pVeterinarioId;
        fechaHora = pFechaHora;
        loteVacuna = pLoteVacuna;
        proximaDosis = pProximaDosis;
        observaciones = pObservaciones;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getJornadaId() { return jornadaId; }
    public void setJornadaId(int pJornadaId) { jornadaId = pJornadaId; }
    public int getMascotaId() { return mascotaId; }
    public void setMascotaId(int pMascotaId) { mascotaId = pMascotaId; }
    public int getDuenoId() { return duenoId; }
    public void setDuenoId(int pDuenoId) { duenoId = pDuenoId; }
    public int getVacunaId() { return vacunaId; }
    public void setVacunaId(int pVacunaId) { vacunaId = pVacunaId; }
    public Integer getVeterinarioId() { return veterinarioId; }
    public void setVeterinarioId(Integer pVeterinarioId) { veterinarioId = pVeterinarioId; }
    public Timestamp getFechaHora() { return fechaHora; }
    public void setFechaHora(Timestamp pFechaHora) { fechaHora = pFechaHora; }
    public String getLoteVacuna() { return loteVacuna; }
    public void setLoteVacuna(String pLoteVacuna) { loteVacuna = pLoteVacuna; }
    public Date getProximaDosis() { return proximaDosis; }
    public void setProximaDosis(Date pProximaDosis) { proximaDosis = pProximaDosis; }
    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String pObservaciones) { observaciones = pObservaciones; }

    @Override
    public String toString() {
        return "RegistroJornadaVacunacion: id=" + id + ", "
                + "\njornadaId=" + jornadaId + ", "
                + "\nmascotaId=" + mascotaId;
    }
}