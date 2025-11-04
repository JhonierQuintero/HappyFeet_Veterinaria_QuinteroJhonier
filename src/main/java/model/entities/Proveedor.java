package model.entities;

import java.sql.Timestamp;

public class Proveedor {
    private int id;
    private String nombreEmpresa;
    private String contacto;
    private String telefono;
    private String email;
    private String direccion;
    private boolean activo;
    private Timestamp fechaRegistro;

    public Proveedor(int pId, String pNombreEmpresa, String pContacto, String pTelefono, String pEmail, String pDireccion, boolean pActivo, Timestamp pFechaRegistro) {
        id = pId;
        nombreEmpresa = pNombreEmpresa;
        contacto = pContacto;
        telefono = pTelefono;
        email = pEmail;
        direccion = pDireccion;
        activo = pActivo;
        fechaRegistro = pFechaRegistro;
    }

    public Proveedor(String pNombreEmpresa, String pContacto, String pTelefono, String pEmail, String pDireccion, boolean pActivo, Timestamp pFechaRegistro) {
        nombreEmpresa = pNombreEmpresa;
        contacto = pContacto;
        telefono = pTelefono;
        email = pEmail;
        direccion = pDireccion;
        activo = pActivo;
        fechaRegistro = pFechaRegistro;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public String getNombreEmpresa() { return nombreEmpresa; }
    public void setNombreEmpresa(String pNombreEmpresa) { nombreEmpresa = pNombreEmpresa; }
    public String getContacto() { return contacto; }
    public void setContacto(String pContacto) { contacto = pContacto; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String pTelefono) { telefono = pTelefono; }
    public String getEmail() { return email; }
    public void setEmail(String pEmail) { email = pEmail; }
    public String getDireccion() { return direccion; }
    public void setDireccion(String pDireccion) { direccion = pDireccion; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean pActivo) { activo = pActivo; }
    public Timestamp getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(Timestamp pFechaRegistro) { fechaRegistro = pFechaRegistro; }

    @Override
    public String toString() {
        return "Proveedor: id=" + id + ", "
                + "\nnombreEmpresa='" + nombreEmpresa + "', "
                + "\ntelefono='" + telefono;
    }
}