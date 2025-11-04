package model.entities;

import model.enums.TipoBeneficio;

public class BeneficioClub {
    private int id;
    private String nombre;
    private String nivelRequerido;
    private int puntosNecesarios;
    private TipoBeneficio tipoBeneficio;
    private double valorBeneficio;
    private boolean activo;

    public BeneficioClub(int pId, String pNombre, String pNivelRequerido, int pPuntosNecesarios, TipoBeneficio pTipoBeneficio, double pValorBeneficio, boolean pActivo) {
        id = pId;
        nombre = pNombre;
        nivelRequerido = pNivelRequerido;
        puntosNecesarios = pPuntosNecesarios;
        tipoBeneficio = pTipoBeneficio;
        valorBeneficio = pValorBeneficio;
        activo = pActivo;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public String getNombre() { return nombre; }
    public void setNombre(String pNombre) { nombre = pNombre; }
    public String getNivelRequerido() { return nivelRequerido; }
    public void setNivelRequerido(String pNivelRequerido) { nivelRequerido = pNivelRequerido; }
    public int getPuntosNecesarios() { return puntosNecesarios; }
    public void setPuntosNecesarios(int pPuntosNecesarios) { puntosNecesarios = pPuntosNecesarios; }
    public TipoBeneficio getTipoBeneficio() { return tipoBeneficio; }
    public void setTipoBeneficio(TipoBeneficio pTipoBeneficio) { tipoBeneficio = pTipoBeneficio; }
    public double getValorBeneficio() { return valorBeneficio; }
    public void setValorBeneficio(double pValorBeneficio) { valorBeneficio = pValorBeneficio; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean pActivo) { activo = pActivo; }

    @Override
    public String toString() {
        return "BeneficioClub: id=" + id + ", "
                + "\nnombre='" + nombre + "', "
                + "\npuntosNecesarios=" + puntosNecesarios;
    }
}