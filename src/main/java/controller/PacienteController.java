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
    
    public void actualizarDueño(Dueño pDueño) {
        dueñoDAO.actualizar(pDueño);
    }

    public void eliminarDueño(int pIdDueño) {
        dueñoDAO.eliminar(pIdDueño);
    }

    public void agregarMascota(Mascota pMascota) {
        mascotaDAO.agregar(pMascota);
    }
    
    public List<Mascota> listarTodasLasMascotas() {
        return mascotaDAO.listar();
    }

    public Mascota buscarMascotaPorId(int pIdMascota) {
        return mascotaDAO.leerPorId(pIdMascota);
    }

    public void actualizarMascota(Mascota pMascota) {
        mascotaDAO.actualizar(pMascota);
    }

    public void eliminarMascota(int pIdMascota) {
        mascotaDAO.eliminar(pIdMascota);
    }

    public List<Mascota> listarMascotasPorDueño(int pDueñoId) {
        return mascotaDAO.listarPorDueño(pDueñoId);
    }
    
    public List<Especie> listarEspecies() {
        return especieDAO.listar();
    }

    public List<Raza> listarRazasPorEspecie(int pEspecieId) {
        return razaDAO.listarPorEspecie(pEspecieId);
    }

    public String transferirMascota(int pIdMascota, String pDocDueñoActual, String pDocDueñoNuevo) {
        Dueño dueñoActual = dueñoDAO.leerPorDocumento(pDocDueñoActual);
        if (dueñoActual == null) {
            return "Error: El dueño actual no fue encontrado.";
        }

        Dueño dueñoNuevo = dueñoDAO.leerPorDocumento(pDocDueñoNuevo);
        if (dueñoNuevo == null) {
            return "Error: El nuevo dueño no fue encontrado.";
        }
        
        if (dueñoActual.getId() == dueñoNuevo.getId()) {
            return "Error: El dueño actual y el nuevo dueño son la misma persona.";
        }

        Mascota mascota = mascotaDAO.leerPorId(pIdMascota);
        if (mascota == null) {
            return "Error: La mascota no fue encontrada.";
        }

        if (mascota.getDueñoId() != dueñoActual.getId()) {
            return "Error de seguridad: La mascota no pertenece al dueño actual indicado.";
        }

        mascota.setDueñoId(dueñoNuevo.getId());
        mascotaDAO.actualizar(mascota);

        return "¡Transferencia exitosa! La mascota '" + mascota.getNombre() + "' ahora pertenece a '" + dueñoNuevo.getNombre() + "'.";
    }
}