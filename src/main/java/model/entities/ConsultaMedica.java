package model.entities;

import java.sql.Timestamp;

public class ConsultaMedica {
    private int id;
    private int mascotaId;
    private int veterinarioId;
    private Integer citaId;
    private Timestamp fechaHora;
    private String motivo;
    private String sintomas;
    private String diagnostico;
    private String recomendaciones;
    private double pesoRegistrado;
    private double temperatura;

    public ConsultaMedica(int pId, int pMascotaId, int pVeterinarioId, Integer pCitaId, Timestamp pFechaHora, String pMotivo, String pSintomas, String pDiagnostico, String pRecomendaciones, double pPesoRegistrado, double pTemperatura) {
        id = pId;
        mascotaId = pMascotaId;
        veterinarioId = pVeterinarioId;
        citaId = pCitaId;
        fechaHora = pFechaHora;
        motivo = pMotivo;
        sintomas = pSintomas;
        diagnostico = pDiagnostico;
        recomendaciones = pRecomendaciones;
        pesoRegistrado = pPesoRegistrado;
        temperatura = pTemperatura;
    }
    
    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public int getMascotaId() { return mascotaId; }
    public void setMascotaId(int pMascotaId) { mascotaId = pMascotaId; }
    public int getVeterinarioId() { return veterinarioId; }
    public void setVeterinarioId(int pVeterinarioId) { veterinarioId = pVeterinarioId; }
    public Integer getCitaId() { return citaId; }
    public void setCitaId(Integer pCitaId) { citaId = pCitaId; }
    public Timestamp getFechaHora() { return fechaHora; }
    public void setFechaHora(Timestamp pFechaHora) { fechaHora = pFechaHora; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String pMotivo) { motivo = pMotivo; }
    public String getSintomas() { return sintomas; }
    public void setSintomas(String pSintomas) { sintomas = pSintomas; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String pDiagnostico) { diagnostico = pDiagnostico; }
    public String getRecomendaciones() { return recomendaciones; }
    public void setRecomendaciones(String pRecomendaciones) { recomendaciones = pRecomendaciones; }
    public double getPesoRegistrado() { return pesoRegistrado; }
    public void setPesoRegistrado(double pPesoRegistrado) { pesoRegistrado = pPesoRegistrado; }
    public double getTemperatura() { return temperatura; }
    public void setTemperatura(double pTemperatura) { temperatura = pTemperatura; }

    @Override
    public String toString() {
        return "ConsultaMedica: id=" + id + ", "
                + "\nmascotaId=" + mascotaId + ", "
                + "\nfechaHora=" + fechaHora + ", "
                + "\ndiagnostico= " + diagnostico;
    }
}
