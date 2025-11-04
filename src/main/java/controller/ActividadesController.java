package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import model.entities.Adopcion;
import model.entities.BeneficioClub;
import model.entities.CanjeBeneficios;
import model.entities.ClubMascotas;
import model.entities.Dueño;
import model.entities.Factura;
import model.entities.Inventario;
import model.entities.JornadaVacunacion;
import model.entities.Mascota;
import model.entities.MascotaAdopcion;
import model.entities.MovimientoInventario;
import model.entities.RegistroJornadaVacunacion;
import model.entities.TransaccionPuntos;
import model.enums.EstadoAdopcion;
import model.enums.EstadoCanje;
import model.enums.TipoMovimientoInventario;
import model.enums.TipoTransaccionPuntos;
import repository.AdopcionDAO;
import repository.BeneficioClubDAO;
import repository.CanjeBeneficiosDAO;
import repository.ClubMascotasDAO;
import repository.FacturaDAO;
import repository.InventarioDAO;
import repository.JornadaVacunacionDAO;
import repository.MascotaAdopcionDAO;
import repository.MascotaDAO;
import repository.MovimientoInventarioDAO;
import repository.RegistroJornadaVacunacionDAO;
import repository.TransaccionPuntosDAO;

public class ActividadesController {

    private MascotaAdopcionDAO mascotaAdopcionDAO;
    private AdopcionDAO adopcionDAO;
    private JornadaVacunacionDAO jornadaVacunacionDAO;
    private RegistroJornadaVacunacionDAO registroJornadaDAO;
    private ClubMascotasDAO clubMascotasDAO;
    private TransaccionPuntosDAO transaccionPuntosDAO;
    private BeneficioClubDAO beneficioClubDAO;
    private CanjeBeneficiosDAO canjeBeneficiosDAO;
    private PacienteController pacienteController;
    private InventarioDAO inventarioDAO;
    private MovimientoInventarioDAO movimientoInventarioDAO;
    private FacturaDAO facturaDAO;
    private MascotaDAO mascotaDAO;

    public ActividadesController() {
        this.mascotaAdopcionDAO = new MascotaAdopcionDAO();
        this.adopcionDAO = new AdopcionDAO();
        this.jornadaVacunacionDAO = new JornadaVacunacionDAO();
        this.registroJornadaDAO = new RegistroJornadaVacunacionDAO();
        this.clubMascotasDAO = new ClubMascotasDAO();
        this.transaccionPuntosDAO = new TransaccionPuntosDAO();
        this.beneficioClubDAO = new BeneficioClubDAO();
        this.canjeBeneficiosDAO = new CanjeBeneficiosDAO();
        this.pacienteController = new PacienteController();
        this.inventarioDAO = new InventarioDAO();
        this.movimientoInventarioDAO = new MovimientoInventarioDAO();
        this.facturaDAO = new FacturaDAO();
        this.mascotaDAO = new MascotaDAO();
    }

    public void ponerMascotaEnAdopcion(MascotaAdopcion pMascotaAdopcion) {
        mascotaAdopcionDAO.agregar(pMascotaAdopcion);
    }

    public List<MascotaAdopcion> listarMascotasParaAdopcion() {
        return mascotaAdopcionDAO.listar().stream()
            .filter(m -> m.getEstado() == EstadoAdopcion.DISPONIBLE)
            .collect(Collectors.toList());
    }

    public String adoptarMascota(int pMascotaAdopcionId, String pDocumentoNuevoDueño) {
        MascotaAdopcion mascotaAdopcion = mascotaAdopcionDAO.leerPorId(pMascotaAdopcionId);
        if (mascotaAdopcion == null || mascotaAdopcion.getEstado() != EstadoAdopcion.DISPONIBLE) {
            return "Error: La mascota seleccionada no esta disponible para adopcion.";
        }
        
        Dueño nuevoDueño = pacienteController.buscarDueñoPorDocumento(pDocumentoNuevoDueño);
        if (nuevoDueño == null) {
            return "Error: El nuevo dueno no fue encontrado.";
        }
        
        Mascota mascota = this.buscarMascotaPorId(mascotaAdopcion.getMascotaId());
        
        mascota.setDueñoId(nuevoDueño.getId());
        pacienteController.actualizarMascota(mascota);
        
        mascotaAdopcion.setEstado(EstadoAdopcion.ADOPTADA);
        mascotaAdopcionDAO.actualizar(mascotaAdopcion);
        
        String contrato = generarContratoTexto(mascota, nuevoDueño);
        Adopcion adopcion = new Adopcion(pMascotaAdopcionId, nuevoDueño.getId(), new Date(System.currentTimeMillis()), contrato);
        adopcionDAO.agregar(adopcion);
        
        generarContratoTxt(adopcion, mascota, nuevoDueño);
        
        return "Adopcion registrada con exito. Contrato generado.";
    }

    private void generarContratoTxt(Adopcion pAdopcion, Mascota pMascota, Dueño pDueño) {
        String nombreArchivo = "contratos/Contrato_Adopcion_" + pMascota.getNombre() + "_" + pDueño.getNumeroDocumento() + ".txt";
        try {
            File directorio = new File("contratos");
            if (!directorio.exists()) { directorio.mkdir(); }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
            writer.write(pAdopcion.getContratoTexto());
            writer.close();
            System.out.println("Contrato guardado en: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al generar el archivo del contrato.");
        }
    }

    private String generarContratoTexto(Mascota pMascota, Dueño pDueño) {
        return "CONTRATO DE ADOPCION RESPONSABLE\n\n" +
               "Fecha: " + new Date(System.currentTimeMillis()) + "\n\n" +
               "Por medio del presente, la Clinica Veterinaria Happy Feet hace entrega de la mascota:\n" +
               "Nombre: " + pMascota.getNombre() + "\n" +
               "ID Mascota: " + pMascota.getId() + "\n\n" +
               "Al adoptante:\n" +
               "Nombre: " + pDueño.getNombre() + "\n" +
               "Documento: " + pDueño.getNumeroDocumento() + "\n\n" +
               "El adoptante se compromete a proporcionar un hogar seguro, cuidados veterinarios y amor a la mascota.\n\n" +
               "Firma Adoptante: ____________________\n" +
               "Firma Clinica: ____________________\n";
    }

    public void crearJornada(JornadaVacunacion pJornada) {
        jornadaVacunacionDAO.agregar(pJornada);
    }

    public List<JornadaVacunacion> listarJornadas() {
        return jornadaVacunacionDAO.listar();
    }
    
    public String registrarVacunaEnJornada(RegistroJornadaVacunacion pRegistro) {
        Inventario vacuna = inventarioDAO.leerPorId(pRegistro.getVacunaId());
        if (vacuna == null || vacuna.getCantidadStock() <= 0) {
            return "Error: La vacuna seleccionada no tiene stock disponible.";
        }
        
        int stockAnterior = vacuna.getCantidadStock();
        vacuna.setCantidadStock(stockAnterior - 1);
        inventarioDAO.actualizar(vacuna);
        
        MovimientoInventario mov = new MovimientoInventario(vacuna.getId(), TipoMovimientoInventario.SALIDA, -1, stockAnterior, vacuna.getCantidadStock(), "Jornada de vacunacion ID: " + pRegistro.getJornadaId(), null, null, "sistema", new Timestamp(System.currentTimeMillis()));
        movimientoInventarioDAO.agregar(mov);
        
        registroJornadaDAO.agregar(pRegistro);
        return "Registro de vacunacion exitoso para mascota ID: " + pRegistro.getMascotaId();
    }

    public String inscribirDueñoAlClub(String pDocumentoDueño) {
        Dueño dueño = pacienteController.buscarDueñoPorDocumento(pDocumentoDueño);
        if (dueño == null) {
            return "Error: El dueno no fue encontrado.";
        }
        if (clubMascotasDAO.leerPorDuenoId(dueño.getId()) != null) {
            return "Error: El dueno ya esta inscrito en el club.";
        }
        ClubMascotas nuevoMiembro = new ClubMascotas(dueño.getId(), 0, new Date(System.currentTimeMillis()), true);
        clubMascotasDAO.agregar(nuevoMiembro);
        return "Dueno inscrito al Club de Mascotas Frecuentes con exito.";
    }

    public ClubMascotas consultarPuntos(String pDocumentoDueño) {
        Dueño dueño = pacienteController.buscarDueñoPorDocumento(pDocumentoDueño);
        if (dueño == null) {
            System.out.println("Error: Dueno no encontrado.");
            return null;
        }
        return clubMascotasDAO.leerPorDuenoId(dueño.getId());
    }

    public String agregarPuntosPorCompra(int pFacturaId) {
        Factura factura = facturaDAO.leerPorId(pFacturaId);
        if (factura == null) {
            return "Error: Factura no encontrada.";
        }
        
        ClubMascotas socio = clubMascotasDAO.leerPorDuenoId(factura.getDuenoId());
        if (socio == null) {
            return "Error: El dueno no es miembro del club.";
        }
        
        int puntosGanados = (int) (factura.getTotal() / 1000);
        if (puntosGanados <= 0) {
            return "El total de la factura no es suficiente para generar puntos.";
        }
        
        int puntosAnteriores = socio.getPuntosDisponibles();
        int puntosNuevos = puntosAnteriores + puntosGanados;
        socio.setPuntosDisponibles(puntosNuevos);
        clubMascotasDAO.actualizar(socio);
        
        String descripcion = "Puntos ganados por compra en factura " + factura.getNumeroFactura();
        TransaccionPuntos transaccion = new TransaccionPuntos(socio.getId(), pFacturaId, puntosGanados, TipoTransaccionPuntos.GANADOS, new Timestamp(System.currentTimeMillis()), descripcion, puntosAnteriores, puntosNuevos);
        transaccionPuntosDAO.agregar(transaccion);
        
        return puntosGanados + " puntos agregados con exito.";
    }
    
    public void agregarBeneficio(BeneficioClub pBeneficio) {
        beneficioClubDAO.agregar(pBeneficio);
    }
    
    public List<BeneficioClub> listarBeneficios() {
        return beneficioClubDAO.listar();
    }
    
    public String canjearBeneficio(int pSocioId, int pBeneficioId) {
        ClubMascotas socio = clubMascotasDAO.leerPorId(pSocioId);
        BeneficioClub beneficio = beneficioClubDAO.leerPorId(pBeneficioId);

        if (socio == null || beneficio == null) {
            return "Error: Socio o beneficio no encontrado.";
        }

        if (socio.getPuntosDisponibles() < beneficio.getPuntosNecesarios()) {
            return "Error: Puntos insuficientes para canjear este beneficio.";
        }
        
        int puntosAnteriores = socio.getPuntosDisponibles();
        int puntosNuevos = puntosAnteriores - beneficio.getPuntosNecesarios();
        socio.setPuntosDisponibles(puntosNuevos);
        clubMascotasDAO.actualizar(socio);
        
        String descripcion = "Canje del beneficio: " + beneficio.getNombre();
        TransaccionPuntos transaccion = new TransaccionPuntos(socio.getId(), null, beneficio.getPuntosNecesarios() * -1, TipoTransaccionPuntos.CANJEADOS, new Timestamp(System.currentTimeMillis()), descripcion, puntosAnteriores, puntosNuevos);
        transaccionPuntosDAO.agregar(transaccion);

        CanjeBeneficios canje = new CanjeBeneficios(0, socio.getId(), beneficio.getId(), new Timestamp(System.currentTimeMillis()), beneficio.getPuntosNecesarios(), EstadoCanje.APLICADO, null);
        canjeBeneficiosDAO.agregar(canje);

        return "Beneficio '" + beneficio.getNombre() + "' canjeado con exito.";
    }
    
    public List<Mascota> listarMascotas() {
        return mascotaDAO.listar();
    }
    
    public Mascota buscarMascotaPorId(int id) {
        return mascotaDAO.leerPorId(id);
    }
    
    public List<Mascota> listarMascotasPorDueño(int pDueñoId) {
        return mascotaDAO.listarPorDueño(pDueñoId);
    }
    
    public Dueño buscarDueñoPorDocumento(String pDoc) {
        return pacienteController.buscarDueñoPorDocumento(pDoc);
    }

    public List<Inventario> listarVacunasDisponibles() {
        return inventarioDAO.listar().stream()
            .filter(p -> p.getProductoTipoId() == 2 && p.getCantidadStock() > 0)
            .collect(Collectors.toList());
    }

    public List<Factura> listarFacturas() {
        return facturaDAO.listar();
    }
}