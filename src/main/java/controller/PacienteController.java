package controller;

import java.util.List;
import model.entities.Dueño;
import model.entities.Especie;
import model.entities.Mascota;
import model.entities.Raza;
import repository.DueñoDAO;
import repository.EspecieDAO;
import repository.MascotaDAO;
import repository.RazaDAO;

public class PacienteController {
    private DueñoDAO dueñoDAO;
    private MascotaDAO mascotaDAO;
    private EspecieDAO especieDAO;
    private RazaDAO razaDAO;

    public PacienteController() {
        this.dueñoDAO = new DueñoDAO();
        this.mascotaDAO = new MascotaDAO();
        this.especieDAO = new EspecieDAO();
        this.razaDAO = new RazaDAO();
    }

    public void agregarDueño(Dueño pDueño) {
        dueñoDAO.agregar(pDueño);
    }
    
    public Dueño buscarDueñoPorDocumento(String pDocumento) {
        return dueñoDAO.leerPorDocumento(pDocumento);
    }
    
    public List<Dueño> listarDueños() {
        return dueñoDAO.listar();
    }

    public List<Especie> listarEspecies() {
        return especieDAO.listar();
    }

    public List<Raza> listarRazasPorEspecie(int pEspecieId) {
        return razaDAO.listarPorEspecie(pEspecieId);
    }

    public void agregarMascota(Mascota pMascota) {
        Dueño dueño = dueñoDAO.leerPorId(pMascota.getDueñoId());
        if (dueño != null && dueño.getActivo()) {
            mascotaDAO.agregar(pMascota);
            System.out.println("¡Mascota registrada exitosamente con ID: " + pMascota.getId() + "!");
        } else {
            System.out.println("No se pudo agregar la mascota. El dueño no existe o no está activo.");
        }
    }
    
    public List<Mascota> listarMascotasPorDueño(int pDueñoId) {
        return mascotaDAO.listarPorDueño(pDueñoId);
    }
}