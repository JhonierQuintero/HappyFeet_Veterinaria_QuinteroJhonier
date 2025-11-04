package model.entities;

import java.sql.Date;
import model.enums.EstadoJornada;

public class JornadaVacunacion {
    private int id;
    private String nombre;
    private Date fecha;
    private String ubicacion;
    private EstadoJornada estado;

    public JornadaVacunacion(int pId, String pNombre, Date pFecha, String pUbicacion, EstadoJornada pEstado) {
        id = pId;
        nombre = pNombre;
        fecha = pFecha;
        ubicacion = pUbicacion;
        estado = pEstado;
    }
    
    public JornadaVacunacion(String pNombre, Date pFecha, String pUbicacion, EstadoJornada pEstado) {
        nombre = pNombre;
        fecha = pFecha;
        ubicacion = pUbicacion;
        estado = pEstado;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public String getNombre() { return nombre; }
    public void setNombre(String pNombre) { nombre = pNombre; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date pFecha) { fecha = pFecha; }
    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String pUbicacion) { ubicacion = pUbicacion; }
    public EstadoJornada getEstado() { return estado; }
    public void setEstado(EstadoJornada pEstado) { estado = pEstado; }

    @Override
    public String toString() {
        return "JornadaVacunacion: id=" + id + ", "
                + "\nnombre='" + nombre + "', "
                + "\nfecha=" + fecha;
    }
}