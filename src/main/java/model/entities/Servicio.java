package model.entities;

public class Servicio {
    private int id;
    private String nombre;
    private String descripcion;
    private String categoria;
    private double precioBase;
    private int duracionEstimadaMinutos;
    private boolean activo;

    public Servicio(int pId, String pNombre, String pDescripcion, String pCategoria, double pPrecioBase, int pDuracionEstimadaMinutos, boolean pActivo) {
        id = pId;
        nombre = pNombre;
        descripcion = pDescripcion;
        categoria = pCategoria;
        precioBase = pPrecioBase;
        duracionEstimadaMinutos = pDuracionEstimadaMinutos;
        activo = pActivo;
    }
    
    public Servicio(String pNombre, String pDescripcion, String pCategoria, double pPrecioBase, int pDuracionEstimadaMinutos, boolean pActivo) {
        nombre = pNombre;
        descripcion = pDescripcion;
        categoria = pCategoria;
        precioBase = pPrecioBase;
        duracionEstimadaMinutos = pDuracionEstimadaMinutos;
        activo = pActivo;
    }

    public int getId() { return id; }
    public void setId(int pId) { id = pId; }
    public String getNombre() { return nombre; }
    public void setNombre(String pNombre) { nombre = pNombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String pDescripcion) { descripcion = pDescripcion; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String pCategoria) { categoria = pCategoria; }
    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double pPrecioBase) { precioBase = pPrecioBase; }
    public int getDuracionEstimadaMinutos() { return duracionEstimadaMinutos; }
    public void setDuracionEstimadaMinutos(int pDuracionEstimadaMinutos) { duracionEstimadaMinutos = pDuracionEstimadaMinutos; }
    public boolean getActivo() { return activo; }
    public void setActivo(boolean pActivo) { activo = pActivo; }

    @Override
    public String toString() {
        return "Servicio: id=" + id + ", "
                + "\nnombre='" + nombre + "', "
                + "\nprecioBase=" + precioBase;
    }
}