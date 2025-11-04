package model.entities;

import java.sql.Date;

public class HistorialMedico {
    private int id;
    private int mascotaId;
    private Date fechaEvento;
    private int eventoTipoId;
    private String descripcion;
    private Integer veterinarioId;
    private Integer consultaId;
    private Integer procedimientoId;

    public HistorialMedico(int pId, int pMascotaId, Date pFechaEvento, int pEventoTipoId, String pDescripcion, Integer pVeterinarioId, Integer pConsultaId, Integer pProcedimientoId) {
        id = pId;
        mascotaId = pMascotaId;
        fechaEvento = pFechaEvento;
        eventoTipoId = pEventoTipoId;
        descripcion = pDescripcion;
        veterinarioId = pVeterinarioId;
        consultaId = pConsultaId;
        procedimientoId = pProcedimientoId;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getMascotaId() { return mascotaId; }
    public void setMascotaId(int pMascotaId) { mascotaId = pMascotaId; }
    public Date getFechaEvento() { return fechaEvento; }
    public void setFechaEvento(Date pFechaEvento) { fechaEvento = pFechaEvento; }
    public int getEventoTipoId() { return eventoTipoId; }
    public void setEventoTipoId(int pEventoTipoId) { eventoTipoId = pEventoTipoId; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String pDescripcion) { descripcion = pDescripcion; }
    public Integer getVeterinarioId() { return veterinarioId; }
    public void setVeterinarioId(Integer pVeterinarioId) { veterinarioId = pVeterinarioId; }
    public Integer getConsultaId() { return consultaId; }
    public void setConsultaId(Integer pConsultaId) { consultaId = pConsultaId; }
    public Integer getProcedimientoId() { return procedimientoId; }
    public void setProcedimientoId(Integer pProcedimientoId) { procedimientoId = pProcedimientoId; }

    @Override
    public String toString() {
        return "HistorialMedico: id=" + id + ", "
                + "\nmascotaId=" + mascotaId + ", "
                + "\nfechaEvento=" + fechaEvento + ", "
                + "\ndescripcion='" + descripcion ;
    }
}
