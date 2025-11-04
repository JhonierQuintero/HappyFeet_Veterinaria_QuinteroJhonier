package controller;

import java.sql.Timestamp;
import java.util.List;
import model.entities.Cita;
import model.entities.CitaEstado;
import model.entities.ConsultaMedica;
import model.entities.HistorialMedico;
import model.entities.Inventario;
import model.entities.Mascota;
import model.entities.Prescripcion;
import model.entities.ProcedimientoEspecial;
import model.entities.Veterinario;
import repository.CitaDAO;
import repository.CitaEstadoDAO;
import repository.ConsultaMedicaDAO;
import repository.HistorialMedicoDAO;
import repository.MascotaDAO;
import repository.PrescripcionDAO;
import repository.ProcedimientoEspecialDAO;
import repository.VeterinarioDAO;

public class CitasController {
    
    private CitaDAO citaDAO;
    private ConsultaMedicaDAO consultaMedicaDAO;
    private ProcedimientoEspecialDAO procedimientoEspecialDAO;
    private HistorialMedicoDAO historialMedicoDAO;
    private PrescripcionDAO prescripcionDAO;
    private MascotaDAO mascotaDAO;
    private VeterinarioDAO veterinarioDAO;
    private CitaEstadoDAO citaEstadoDAO;
    private InventarioController inventarioController;

    public CitasController() {
        this.citaDAO = new CitaDAO();
        this.consultaMedicaDAO = new ConsultaMedicaDAO();
        this.procedimientoEspecialDAO = new ProcedimientoEspecialDAO();
        this.historialMedicoDAO = new HistorialMedicoDAO();
        this.prescripcionDAO = new PrescripcionDAO();
        this.mascotaDAO = new MascotaDAO();
        this.veterinarioDAO = new VeterinarioDAO();
        this.citaEstadoDAO = new CitaEstadoDAO();
        this.inventarioController = new InventarioController();
    }
    
    public void agendarCita(Cita pCita) {
        citaDAO.agregar(pCita);
    }

    public void registrarConsulta(ConsultaMedica pConsulta) {
        consultaMedicaDAO.agregar(pConsulta);
        if (pConsulta.getId() > 0) {
            HistorialMedico historial = new HistorialMedico(0, pConsulta.getMascotaId(), new java.sql.Date(pConsulta.getFechaHora().getTime()), 2, "Consulta: " + pConsulta.getDiagnostico(), pConsulta.getVeterinarioId(), pConsulta.getId(), null);
            historialMedicoDAO.agregar(historial);
        }
    }
    
    public void registrarProcedimiento(ProcedimientoEspecial pProcedimiento) {
        procedimientoEspecialDAO.agregar(pProcedimiento);
    }
    
    public String crearPrescripcion(Prescripcion pPrescripcion) {
        String resultadoStock = inventarioController.reducirStockPorPrescripcion(pPrescripcion.getProductoId(), pPrescripcion.getCantidad(), pPrescripcion.getConsultaId());
        if (!resultadoStock.equals("Exito")) {
            return resultadoStock;
        }
        prescripcionDAO.agregar(pPrescripcion);
        if (pPrescripcion.getId() > 0) {
            return "Prescripcion creada y stock actualizado con exito.";
        } else {
            return "Error al guardar la prescripcion, el stock no fue modificado.";
        }
    }
    
    public List<Cita> listarCitas() {
        return citaDAO.listar();
    }

    public Cita buscarCitaPorId(int pIdCita) {
        return citaDAO.leerPorId(pIdCita);
    }

    public void actualizarCita(Cita pCita) {
        citaDAO.actualizar(pCita);
    }
    
    public List<HistorialMedico> verHistorialDeMascota(int pIdMascota) {
        return historialMedicoDAO.listarPorMascota(pIdMascota);
    }

    public void agregarVeterinario(Veterinario pVet) {
        veterinarioDAO.agregar(pVet);
    }
    
    public Veterinario buscarVeterinarioPorId(int pId) {
        return veterinarioDAO.leerPorId(pId);
    }

    public void actualizarVeterinario(Veterinario pVet) {
        veterinarioDAO.actualizar(pVet);
    }
    
    public void eliminarVeterinario(int pId) {
        veterinarioDAO.eliminar(pId);
    }

    public boolean existeMascota(int id) {
        return mascotaDAO.leerPorId(id) != null;
    }
    
    public Mascota buscarMascotaPorId(int id) {
        return mascotaDAO.leerPorId(id);
    }
    
    public List<Mascota> listarMascotas() {
        return mascotaDAO.listar();
    }

    public boolean existeVeterinario(int id) {
        return veterinarioDAO.leerPorId(id) != null;
    }
    
    public List<Veterinario> listarVeterinarios() {
        return veterinarioDAO.listar();
    }
    
    public List<Inventario> listarProductosDeInventario() {
        return inventarioController.listarProductos();
    }
    
    public List<CitaEstado> listarEstadosDeCita() {
        return citaEstadoDAO.listar();
    }
}