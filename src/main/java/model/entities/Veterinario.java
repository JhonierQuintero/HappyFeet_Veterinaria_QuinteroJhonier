package model.entities;

import java.sql.Date;

public class Veterinario {

    private int id;
    private String nombreCompleto;
    private String documentoIdentidad;
    private String licenciaProfesional;
    private String especialidad;
    private String telefono;
    private String email;
    private Date fechaContratacion;
    private boolean activo;

    public Veterinario(int pId, String pNombreCompleto, String pDocumentoIdentidad, String pLicenciaProfesional, String pEspecialidad, String pTelefono, String pEmail, Date pFechaContratacion, boolean pActivo) {
        id = pId;
        nombreCompleto = pNombreCompleto;
        documentoIdentidad = pDocumentoIdentidad;
        licenciaProfesional = pLicenciaProfesional;
        especialidad = pEspecialidad;
        telefono = pTelefono;
        email = pEmail;
        fechaContratacion = pFechaContratacion;
        activo = pActivo;
    }
    
    public Veterinario(String pNombreCompleto, String pDocumentoIdentidad, String pLicenciaProfesional, String pEspecialidad, String pTelefono, String pEmail, Date pFechaContratacion, boolean pActivo) {
        nombreCompleto = pNombreCompleto;
        documentoIdentidad = pDocumentoIdentidad;
        licenciaProfesional = pLicenciaProfesional;
        especialidad = pEspecialidad;
        telefono = pTelefono;
        email = pEmail;
        fechaContratacion = pFechaContratacion;
        activo = pActivo;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String pNombreCompleto) { nombreCompleto = pNombreCompleto; }
    public String getDocumentoIdentidad() { return documentoIdentidad; }
    public void setDocumentoIdentidad(String pDocumentoIdentidad) { documentoIdentidad = pDocumentoIdentidad; }
    public String getLicenciaProfesional() { return licenciaProfesional; }
    public void setLicenciaProfesional(String pLicenciaProfesional) { licenciaProfesional = pLicenciaProfesional; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String pEspecialidad) { especialidad = pEspecialidad; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String pTelefono) { telefono = pTelefono; }
    public String getEmail() { return email; }
    public void setEmail(String pEmail) { email = pEmail; }
    public Date getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(Date pFechaContratacion) { fechaContratacion = pFechaContratacion; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean pActivo) { activo = pActivo; }

    @Override
    public String toString() {
        return "Veterinario{" + "id=" + id + ", "
                + "\nnombreCompleto='" + nombreCompleto+ ", "
                + "\nlicenciaProfesional='" + licenciaProfesional + ", "
                + "\nactivo=" + activo + '}';
    }
}

