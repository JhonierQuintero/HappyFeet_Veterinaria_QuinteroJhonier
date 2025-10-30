package model.entities;

import java.sql.Date;
import model.enums.Sexo;

public class Mascota {

    private int id;
    private int dueñoId;
    private String nombre;
    private int razaId;
    private Date fechaNacimiento;
    private Sexo sexo;
    private double pesoActual;
    private String microchip;
    private String tatuaje;
    private String urlFoto;
    private String alergias;
    private String condicionesPreexistentes;
    private Date fechaRegistro;
    private boolean activo;
    
    public Mascota(int pId, int pDueñoId, String pNombre, int pRazaId, Date pFechaNacimiento, Sexo pSexo, double pPesoActual,
           String pMicrochip, String pTatuaje, String pUrlFoto, String pAlergias, String pCondicionesPreexistentes,
           Date pFechaRegistro, boolean pActivo){
        
        id = pId;
        dueñoId = pDueñoId;
        nombre = pNombre;
        razaId = pRazaId;
        fechaNacimiento = pFechaNacimiento;
        sexo = pSexo;
        pesoActual = pPesoActual;
        microchip = pMicrochip;
        tatuaje = pTatuaje;
        urlFoto = pUrlFoto;
        alergias = pAlergias;
        condicionesPreexistentes = pCondicionesPreexistentes;
        fechaRegistro = pFechaRegistro;
        activo = pActivo;
    }
    
    public Mascota(int pDueñoId, String pNombre, int pRazaId, Date pFechaNacimiento, Sexo pSexo, double pPesoActual,
           String pMicrochip, String pTatuaje, String pUrlFoto, String pAlergias, String pCondicionesPreexistentes,
           Date pFechaRegistro, boolean pActivo){
        
        dueñoId = pDueñoId;
        nombre = pNombre;
        razaId = pRazaId;
        fechaNacimiento = pFechaNacimiento;
        sexo = pSexo;
        pesoActual = pPesoActual;
        microchip = pMicrochip;
        tatuaje = pTatuaje;
        urlFoto = pUrlFoto;
        alergias = pAlergias;
        condicionesPreexistentes = pCondicionesPreexistentes;
        fechaRegistro = pFechaRegistro;
        activo = pActivo;
    }
    
    
    public int getId() { return id; }
    public int getDueñoId() { return dueñoId; }
    public String getNombre() { return nombre; }
    public int getRazaId() { return razaId; }
    public Date getFechaNacimiento() { return fechaNacimiento; }
    public Sexo getSexo() { return sexo; }
    public double getPesoActual() { return pesoActual; }
    public String getMicrochip() { return microchip; }
    public String getTatuaje() { return tatuaje; }
    public String getUrlFoto() { return urlFoto; }
    public String getAlergias() { return alergias; }
    public String getCondicionesPreexistentes() { return condicionesPreexistentes; }
    public Date getFechaRegistro() { return fechaRegistro; }
    public boolean getActivo() { return activo; }

    //-------------------------------------------------------------------------------
    
    public void setId(int pId) { id = pId; }
    public void setDueñoId(int pDueñoId) { dueñoId = pDueñoId; }
    public void setNombre(String pNombre) { nombre = pNombre; }
    public void setRazaId(int pRazaId) { razaId = pRazaId; }
    public void setFechaNacimiento(Date pFechaNacimiento) { fechaNacimiento = pFechaNacimiento; }
    public void setSexo(Sexo sexo) { this.sexo = sexo; }
    public void setPesoActual(double pPesoActual) { pesoActual = pPesoActual; }
    public void setMicrochip(String pMicrochip) { microchip = pMicrochip; }
    public void setTatuaje(String pTatuaje) { tatuaje = pTatuaje; }
    public void setUrlFoto(String pUrlFoto) { urlFoto = pUrlFoto; }
    public void setAlergias(String pAlergias) { alergias = pAlergias; }
    public void setCondicionesPreexistentes(String condicionesPreexistentes) { this.condicionesPreexistentes = condicionesPreexistentes; }
    public void setFechaRegistro(Date pFechaRegistro) { fechaRegistro = pFechaRegistro; }
    public void setActivo(boolean pActivo) { activo = pActivo; }

    @Override
    public String toString() {
        return "Mascota: id = " + id +
               ", \ndueñoId = " + dueñoId +
               ", \nnombre = " + nombre +
               ", \nrazaId = " + razaId +
               ", \nfecha de nacimiento = " + fechaNacimiento +
               ", \nsexo = " + sexo +
               ", \npeso actual = " + pesoActual +
               ", \nmicrochip = " + microchip +
               ", \ntatuaje = " + tatuaje +
               ", \nurl foto = " + urlFoto +
               ", \nalergias = " + alergias +
               ", \ncondiciones preexistentes = " + condicionesPreexistentes +
               ", \nfecha de registro = " + fechaRegistro +
               ", \nestado = " + activo;
    }
}