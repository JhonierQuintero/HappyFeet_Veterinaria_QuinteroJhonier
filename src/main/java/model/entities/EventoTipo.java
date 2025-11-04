package model.entities;

public class EventoTipo {
    private int id;
    private String nombre;
    private String descripcion;

    public EventoTipo(int pId, String pNombre, String pDescripcion) {
        id = pId;
        nombre = pNombre;
        descripcion = pDescripcion;
    }
    
    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public String getNombre() { return nombre; }
    public void setNombre(String pNombre) { nombre = pNombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String pDescripcion) { descripcion = pDescripcion; }

    @Override
    public String toString() {
        return "EventoTipo{id=" + id + ", nombre='" + nombre + "'}";
    }
}