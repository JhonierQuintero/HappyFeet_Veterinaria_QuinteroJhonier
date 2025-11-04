package model.entities;

import java.sql.Timestamp;
import model.enums.EstadoProcedimiento;

public class ProcedimientoEspecial {
    private int id;
    private int mascotaId;
    private int veterinarioId;
    private String nombreProcedimiento;
    private Timestamp fechaHora;
    private String informacionPreoperatoria;
    private String detalleProcedimiento;
    private String seguimientoPostoperatorio;
    private EstadoProcedimiento estado;
    private double costoProcedimiento;

    public ProcedimientoEspecial(int pId, int pMascotaId, int pVeterinarioId, String pNombreProcedimiento, Timestamp pFechaHora, String pInformacionPreoperatoria, String pDetalleProcedimiento, String pSeguimientoPostoperatorio, EstadoProcedimiento pEstado, double pCostoProcedimiento) {
        id = pId;
        mascotaId = pMascotaId;
        veterinarioId = pVeterinarioId;
        nombreProcedimiento = pNombreProcedimiento;
        fechaHora = pFechaHora;
        informacionPreoperatoria = pInformacionPreoperatoria;
        detalleProcedimiento = pDetalleProcedimiento;
        seguimientoPostoperatorio = pSeguimientoPostoperatorio;
        estado = pEstado;
        costoProcedimiento = pCostoProcedimiento;
    }
    
    public ProcedimientoEspecial(int pMascotaId, int pVeterinarioId, String pNombreProcedimiento, Timestamp pFechaHora, String pInformacionPreoperatoria, String pDetalleProcedimiento, String pSeguimientoPostoperatorio, EstadoProcedimiento pEstado, double pCostoProcedimiento) {
        mascotaId = pMascotaId;
        veterinarioId = pVeterinarioId;
        nombreProcedimiento = pNombreProcedimiento;
        fechaHora = pFechaHora;
        informacionPreoperatoria = pInformacionPreoperatoria;
        detalleProcedimiento = pDetalleProcedimiento;
        seguimientoPostoperatorio = pSeguimientoPostoperatorio;
        estado = pEstado;
        costoProcedimiento = pCostoProcedimiento;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getMascotaId() { return mascotaId; }
    public void setMascotaId(int pMascotaId) { mascotaId = pMascotaId; }
    public int getVeterinarioId() { return veterinarioId; }
    public void setVeterinarioId(int pVeterinarioId) { veterinarioId = pVeterinarioId; }
    public String getNombreProcedimiento() { return nombreProcedimiento; }
    public void setNombreProcedimiento(String pNombreProcedimiento) { nombreProcedimiento = pNombreProcedimiento; }
    public Timestamp getFechaHora() { return fechaHora; }
    public void setFechaHora(Timestamp pFechaHora) { fechaHora = pFechaHora; }
    public String getInformacionPreoperatoria() { return informacionPreoperatoria; }
    public void setInformacionPreoperatoria(String pInformacionPreoperatoria) { informacionPreoperatoria = pInformacionPreoperatoria; }
    public String getDetalleProcedimiento() { return detalleProcedimiento; }
    public void setDetalleProcedimiento(String pDetalleProcedimiento) { detalleProcedimiento = pDetalleProcedimiento; }
    public String getSeguimientoPostoperatorio() { return seguimientoPostoperatorio; }
    public void setSeguimientoPostoperatorio(String pSeguimientoPostoperatorio) { seguimientoPostoperatorio = pSeguimientoPostoperatorio; }
    public EstadoProcedimiento getEstado() { return estado; }
    public void setEstado(EstadoProcedimiento pEstado) { estado = pEstado; }
    public double getCostoProcedimiento() { return costoProcedimiento; }
    public void setCostoProcedimiento(double pCostoProcedimiento) { costoProcedimiento = pCostoProcedimiento; }

    @Override
    public String toString() {
        return "ProcedimientoEspecial: id=" + id + ", "
                + "\nnombreProcedimiento='" + nombreProcedimiento + "', "
                + "\nestado=" + estado;
    }
}