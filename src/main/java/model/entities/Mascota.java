package model.entities;

import java.sql.Date;
import model.enums.Sexo;

public class Mascota {
    /*
    id INT AUTO_INCREMENT PRIMARY KEY,
  dueno_id INT NOT NULL,
  nombre VARCHAR(100) NOT NULL,
  raza_id INT NOT NULL,
  fecha_nacimiento DATE,
  sexo ENUM('Macho', 'Hembra') NOT NULL,
  peso_actual DECIMAL(5,2),
  microchip VARCHAR(50) UNIQUE,
  tatuaje VARCHAR(50),
  url_foto VARCHAR(255),
  alergias TEXT,
  condiciones_preexistentes TEXT,
  fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
  activo BOOLEAN DEFAULT TRUE,
    */
    
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
    
    public int getId(){return id;}
    public int getDueñoId(){return dueñoId;}
    public int getRazaId(){return razaId;}

    
}
