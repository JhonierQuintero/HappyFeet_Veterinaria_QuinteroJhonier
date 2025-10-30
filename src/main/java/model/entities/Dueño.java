package model.entities;

import java.sql.Date;

public class Dueño {
    private int id;
    private String nombre;
    private String numeroDocumento;
    private String direccion;
    private String telefono;
    private String email;
    private String contactoEmergencia;
    private Date fechaRegistro;
    private boolean activo;
    
    public Dueño (int pId, String pNombre, String pNumeroDoucmento, String pDireccion, String pTelefono, 
            String pEmail, String pContactoEmergencia, Date pFechaRegistro, boolean pActivo){
        
        id = pId;
        nombre = pNombre;
        numeroDocumento = pNumeroDoucmento;
        direccion = pDireccion;
        telefono = pTelefono;
        email = pEmail;
        contactoEmergencia = pContactoEmergencia;
        fechaRegistro = pFechaRegistro;
        activo = pActivo;
    }
    
    public Dueño (String pNombre, String pNumeroDoucmento, String pDireccion, String pTelefono, 
            String pEmail, String pContactoEmergencia, Date pFechaRegistro, boolean pActivo){
        
        nombre = pNombre;
        numeroDocumento = pNumeroDoucmento;
        direccion = pDireccion;
        telefono = pTelefono;
        email = pEmail;
        contactoEmergencia = pContactoEmergencia;
        fechaRegistro = pFechaRegistro;
        activo = pActivo;
    }
    
    public int getId(){return id;}
    public String getNombre(){return nombre;}
    public String getNumeroDocumento(){return numeroDocumento;}
    public String getDireccion(){return direccion;}
    public String getTelefono(){return telefono;}
    public String getEmail(){return email;}
    public String getContactoEmergencia(){return contactoEmergencia;}
    public Date getFechaRegistro(){return fechaRegistro;}
    public boolean getActivo(){return activo;}
    
    public void setId(int id){ this.id = id; }
    public void setNombre(String nombre){ this.nombre = nombre; }
    public void setNumeroDocumento(String numeroDocumento){ this.numeroDocumento = numeroDocumento; }
    public void setDireccion(String direccion){ this.direccion = direccion; }
    public void setTelefono(String telefono){ this.telefono = telefono; }
    public void setEmail(String email){ this.email = email; }
    public void setContactoEmergencia(String contactoEmergencia){ this.contactoEmergencia = contactoEmergencia; }
    public void setFechaRegistro(Date fechaRegistro){ this.fechaRegistro = fechaRegistro; }
    public void setActivo(boolean activo){ this.activo = activo; }
    
    @Override
    public String toString(){
        return "Dueño: id = "+id+", \nnombre = "+nombre+", \nnumero de documento = "+numeroDocumento+", \ndireccion = "+direccion+", \ntelefono = "+telefono+", "
                + "\nemail = "+email+", \ncontacto de emergencia = "+contactoEmergencia+", \nfecha de registro = "+fechaRegistro+", "
                + "\nestado = "+activo;
    }
}
