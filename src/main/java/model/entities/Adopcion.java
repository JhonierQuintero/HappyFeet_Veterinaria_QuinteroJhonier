package model.entities;

import java.sql.Date;

public class Adopcion {
    private int id;
    private int mascotaAdopcionId;
    private int duenoId;
    private Date fechaAdopcion;
    private String contratoTexto;

    public Adopcion(int pId, int pMascotaAdopcionId, int pDuenoId, Date pFechaAdopcion, String pContratoTexto) {
        id = pId;
        mascotaAdopcionId = pMascotaAdopcionId;
        duenoId = pDuenoId;
        fechaAdopcion = pFechaAdopcion;
        contratoTexto = pContratoTexto;
    }

    public Adopcion(int pMascotaAdopcionId, int pDuenoId, Date pFechaAdopcion, String pContratoTexto) {
        mascotaAdopcionId = pMascotaAdopcionId;
        duenoId = pDuenoId;
        fechaAdopcion = pFechaAdopcion;
        contratoTexto = pContratoTexto;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getMascotaAdopcionId() { return mascotaAdopcionId; }
    public void setMascotaAdopcionId(int pMascotaAdopcionId) { mascotaAdopcionId = pMascotaAdopcionId; }
    public int getDuenoId() { return duenoId; }
    public void setDuenoId(int pDuenoId) { duenoId = pDuenoId; }
    public Date getFechaAdopcion() { return fechaAdopcion; }
    public void setFechaAdopcion(Date pFechaAdopcion) { fechaAdopcion = pFechaAdopcion; }
    public String getContratoTexto() { return contratoTexto; }
    public void setContratoTexto(String pContratoTexto) { contratoTexto = pContratoTexto; }

    @Override
    public String toString() {
        return "Adopcion: id=" + id + ", "
                + "\nmascotaAdopcionId=" + mascotaAdopcionId + ", "
                + "\nduenoId=" + duenoId ;
    }
}