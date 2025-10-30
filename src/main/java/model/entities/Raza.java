package model.entities;

public class Raza {
    private int id;
    private int especieId;
    private String nombre;
    private String caracteristicas;
    
    public Raza(int pId, int pEspecieId, String pNombre, String pCaracteristicas){
        id = pId;
        especieId = pEspecieId;
        nombre = pNombre;
        caracteristicas = pCaracteristicas;
    }
    
    public Raza(int pEspecieId, String pNombre, String pCaracteristicas){
        especieId = pEspecieId;
        nombre = pNombre;
        caracteristicas = pCaracteristicas;
    }
    
    public int getId(){return id;}
    public int getEspecieId(){return especieId;}
    public String getNombre(){return nombre;}
    public String getCaracteristicas(){return caracteristicas;}
    
    
    public void setId(int pId){id = pId;}
    public void setEspecieId(int pEspecieId){especieId = pEspecieId;}
    public void setNombre(String pNombre){nombre = pNombre;}
    public void setCaracteristicas(String pCaracteristicas){caracteristicas = pCaracteristicas;}
    
    @Override
    public String toString(){
        return "Raza: id = "+id+", "
                + "\nespecie id = "+especieId+", "
                + "\nnombre = "+nombre+", "
                + "\ncaracteristicas = "+caracteristicas;
    }
}
