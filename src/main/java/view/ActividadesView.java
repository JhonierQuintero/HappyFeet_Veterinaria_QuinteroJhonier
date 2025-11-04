package view;

import controller.ActividadesController;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import model.entities.BeneficioClub;
import model.entities.ClubMascotas;
import model.entities.Dueño;
import model.entities.Factura;
import model.entities.Inventario;
import model.entities.JornadaVacunacion;
import model.entities.Mascota;
import model.entities.MascotaAdopcion;
import model.entities.RegistroJornadaVacunacion;
import model.enums.EstadoAdopcion;
import model.enums.EstadoJornada;
import model.enums.TipoBeneficio;

public class ActividadesView {
    private ActividadesController controller;
    private Scanner scanner;

    public ActividadesView() {
        this.controller = new ActividadesController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Modulo de Actividades Especiales ---");
            System.out.println("1. Gestion de Adopciones");
            System.out.println("2. Gestion de Jornadas de Vacunacion");
            System.out.println("3. Gestion del Club de Mascotas Frecuentes");
            System.out.println("0. Volver al Menu Principal");

            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1: menuAdopciones(); break;
                case 2: menuJornadas(); break;
                case 3: menuClubMascotas(); break;
                case 0: System.out.println("Volviendo..."); break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private void menuAdopciones() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Gestion de Adopciones ---");
            System.out.println("1. Poner mascota en adopcion");
            System.out.println("2. Listar mascotas disponibles");
            System.out.println("3. Registrar una adopcion");
            System.out.println("0. Volver");
            
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());
            
            switch (opcion) {
                case 1: ponerMascotaEnAdopcion(); break;
                case 2: listarMascotasEnAdopcion(); break;
                case 3: registrarAdopcion(); break;
                case 0: break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private void ponerMascotaEnAdopcion() {
        System.out.println("\n--- Poner Mascota en Adopcion ---");
        int mascotaId = seleccionarMascota();
        if (mascotaId == -1) return;
        
        System.out.print("Historia de la mascota: ");
        String historia = scanner.nextLine();
        
        MascotaAdopcion ma = new MascotaAdopcion(mascotaId, new Date(System.currentTimeMillis()), EstadoAdopcion.DISPONIBLE, historia);
        controller.ponerMascotaEnAdopcion(ma);
        System.out.println("Mascota puesta en adopcion con exito.");
    }

    private void listarMascotasEnAdopcion() {
        System.out.println("\n--- Mascotas Disponibles para Adopcion ---");
        
        List<MascotaAdopcion> disponibles = controller.listarMascotasParaAdopcion();
        
        if (disponibles.isEmpty()) {
            System.out.println("No hay mascotas disponibles para adoptar en este momento.");
        
        } else {
            List<Mascota> todasLasMascotas = controller.listarMascotas();
            disponibles.forEach(ma -> {
                Mascota m = todasLasMascotas.stream()
                                             .filter(mascota -> mascota.getId() == ma.getMascotaId())
                                             .findFirst().orElse(null);
                if (m != null) {
                    System.out.println("ID Adopcion: " + ma.getId() + " | Nombre: " + m.getNombre());
                }
            });
        }
    }

    private void registrarAdopcion() {
        System.out.println("\n--- Registrar una Adopcion ---");
        listarMascotasEnAdopcion();
        
        System.out.print("Ingrese el ID de Adopcion de la mascota a adoptar: ");
        int adopcionId = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Ingrese el documento del nuevo dueno: ");
        String doc = scanner.nextLine();
        
        String resultado = controller.adoptarMascota(adopcionId, doc);
        System.out.println(resultado);
    }

    private void menuJornadas() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Gestion de Jornadas de Vacunacion ---");
            System.out.println("1. Crear nueva jornada");
            System.out.println("2. Iniciar registro rapido para una jornada");
            System.out.println("0. Volver");
            
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());
            
            switch (opcion) {
                case 1: crearJornada(); break;
                case 2: iniciarRegistroJornada(); break;
                case 0: break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private void crearJornada() {
        System.out.println("\n--- Crear Nueva Jornada de Vacunacion ---");
        
        System.out.print("Nombre de la jornada (ej. Vacunacion Canina Anual): ");
        String nombre = scanner.nextLine();
        
        System.out.print("Fecha de la jornada (AAAA-MM-DD): ");
        Date fecha = Date.valueOf(scanner.nextLine());
        
        System.out.print("Ubicacion: ");
        String ubicacion = scanner.nextLine();
        
        JornadaVacunacion jv = new JornadaVacunacion(nombre, fecha, ubicacion, EstadoJornada.PLANIFICADA);
        controller.crearJornada(jv);
        System.out.println("Jornada creada con exito.");
    }
    
    private void iniciarRegistroJornada() {
        System.out.println("\n--- Registro Rapido de Vacunacion ---");
        int jornadaId = seleccionarJornada();
        if (jornadaId == -1) return;
        
        String continuar = "S";
        
        while (continuar.equalsIgnoreCase("S")) {
            System.out.println("\n-- Nuevo Registro --");
            System.out.print("Documento del dueno: ");
            String doc = scanner.nextLine();
            
            Dueño dueño = controller.buscarDueñoPorDocumento(doc);
            
            if (dueño == null) {
                System.out.println("Dueno no encontrado. Registre al dueno primero.");
                continue;
            }
            
            int mascotaId = seleccionarMascotaDeDueño(dueño.getId());
            if (mascotaId == -1) continue;
            
            int vacunaId = seleccionarVacuna();
            if (vacunaId == -1) continue;
            
            System.out.print("Lote de la vacuna: ");
            String lote = scanner.nextLine();
            
            RegistroJornadaVacunacion reg = new RegistroJornadaVacunacion(jornadaId, mascotaId, dueño.getId(), vacunaId, null, new Timestamp(System.currentTimeMillis()), lote, null, "");
            String resultado = controller.registrarVacunaEnJornada(reg);
            System.out.println(resultado);
            
            System.out.print("\nDesea registrar otra mascota? (S/N): ");
            continuar = scanner.nextLine();
        }
    }

    private void menuClubMascotas() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Gestion del Club de Mascotas Frecuentes ---");
            System.out.println("1. Inscribir un dueno al club");
            System.out.println("2. Consultar puntos de un dueno");
            System.out.println("3. Registrar puntos por una compra");
            System.out.println("4. Canjear puntos por un beneficio");
            System.out.println("5. Administrar beneficios del club");
            System.out.println("0. Volver");
            
            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());
            
            switch (opcion) {
                case 1: inscribirDueño(); break;
                case 2: consultarPuntos(); break;
                case 3: registrarPuntosPorCompra(); break;
                case 4: canjearPuntos(); break;
                case 5: gestionarBeneficios(); break;
                case 0: break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private void inscribirDueño() {
        System.out.println("\n--- Inscribir Dueno al Club ---");
        
        System.out.print("Ingrese el documento del dueno: ");
        String doc = scanner.nextLine();
        
        String resultado = controller.inscribirDueñoAlClub(doc);
        System.out.println(resultado);
    }
    
    private void consultarPuntos() {
        System.out.println("\n--- Consultar Puntos ---");
        System.out.print("Ingrese el documento del dueno: ");
        String doc = scanner.nextLine();
        
        ClubMascotas socio = controller.consultarPuntos(doc);
        
        if (socio == null) {
            System.out.println("El dueno no es miembro del club o no fue encontrado.");
        } else {
            System.out.println("El cliente tiene " + socio.getPuntosDisponibles() + " puntos disponibles.");
        }
    }
    
    private void registrarPuntosPorCompra() {
        System.out.println("\n--- Registrar Puntos por Compra ---");
        
        List<Factura> facturas = controller.listarFacturas();
        
        if (facturas.isEmpty()) {
            System.out.println("No hay facturas registradas en el sistema.");
            return;
        }
        
        facturas.forEach(f -> System.out.println("ID: " + f.getId() + " | Numero: " + f.getNumeroFactura() + " | Total: " + f.getTotal()));
        
        System.out.print("Ingrese el ID de la factura para agregar puntos: ");
        int facturaId = Integer.parseInt(scanner.nextLine());
        
        String resultado = controller.agregarPuntosPorCompra(facturaId);
        System.out.println(resultado);
    }
    
    private void canjearPuntos() {
        System.out.println("\n--- Canjear Puntos por Beneficio ---");
        System.out.print("Ingrese el documento del dueno: ");
        String doc = scanner.nextLine();
        
        ClubMascotas socio = controller.consultarPuntos(doc);
        if (socio == null) {
            System.out.println("El dueno no es miembro del club o no fue encontrado.");
            return;
        }
        
        System.out.println("Puntos disponibles: " + socio.getPuntosDisponibles());
        
        List<BeneficioClub> beneficios = controller.listarBeneficios();
        
        if (beneficios.isEmpty()) {
            System.out.println("No hay beneficios disponibles para canjear.");
            return;
        }

        System.out.println("--- Beneficios Disponibles ---");
        
        beneficios.forEach(b -> System.out.println("ID: " + b.getId() + " | Nombre: " + b.getNombre() + " | Puntos Requeridos: " + b.getPuntosNecesarios()));
        
        System.out.print("Ingrese el ID del beneficio a canjear: ");
        int beneficioId = Integer.parseInt(scanner.nextLine());
        
        String resultado = controller.canjearBeneficio(socio.getId(), beneficioId);
        System.out.println(resultado);
    }
    
    private void gestionarBeneficios() {
        System.out.println("\n--- Administracion de Beneficios del Club ---");
        System.out.println("1. Agregar nuevo beneficio");
        System.out.println("2. Listar todos los beneficios");
        System.out.print("Seleccione una opcion: ");
        int opcion = Integer.parseInt(scanner.nextLine());
        
        if (opcion == 1) {
            agregarBeneficio();
        } else if (opcion == 2) {
            listarBeneficios();
        }
    }

    private void agregarBeneficio() {
        System.out.println("\n--- Agregar Nuevo Beneficio ---");
        System.out.print("Nombre del beneficio (ej. Descuento 10% en Comida): ");
        String nombre = scanner.nextLine();
        
        System.out.print("Puntos necesarios para canjear: ");
        int puntos = Integer.parseInt(scanner.nextLine());
        
        BeneficioClub beneficio = new BeneficioClub(0, nombre, "Bronce", puntos, TipoBeneficio.DESCUENTO, 0, true);
        controller.agregarBeneficio(beneficio);
        
        System.out.println("Beneficio agregado con exito.");
    }

    private void listarBeneficios() {
        System.out.println("\n--- Lista de Beneficios del Club ---");
        
        List<BeneficioClub> beneficios = controller.listarBeneficios();
        
        if (beneficios.isEmpty()) {
            System.out.println("No hay beneficios registrados.");
        
        } else {
            beneficios.forEach(b -> System.out.println("ID: " + b.getId() + " | Nombre: " + b.getNombre() + " | Puntos: " + b.getPuntosNecesarios()));
        }
    }

    private int seleccionarMascota() {
        List<Mascota> mascotas = controller.listarMascotas();
        
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas.");
            return -1;
        }
        
        System.out.println("--- Seleccione una Mascota ---");
        
        mascotas.forEach(m -> System.out.println("[" + m.getId() + "] " + m.getNombre()));
        
        System.out.print("Ingrese el ID de la mascota: ");
        return Integer.parseInt(scanner.nextLine());
    }
    
    private int seleccionarMascotaDeDueño(int dueñoId) {
        
        List<Mascota> mascotas = controller.listarMascotasPorDueño(dueñoId);
        
        if (mascotas.isEmpty()) {
            System.out.println("Este dueno no tiene mascotas registradas.");
            return -1;
        }
        
        System.out.println("--- Seleccione la Mascota ---");
        
        mascotas.forEach(m -> System.out.println("[" + m.getId() + "] " + m.getNombre()));
        
        System.out.print("Ingrese el ID de la mascota: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private int seleccionarJornada() {
        
        List<JornadaVacunacion> jornadas = controller.listarJornadas();
        
        if (jornadas.isEmpty()) {
            System.out.println("No hay jornadas creadas.");
            return -1;
        }
        
        System.out.println("--- Seleccione una Jornada ---");
        
        jornadas.forEach(j -> System.out.println("[" + j.getId() + "] " + j.getNombre() + " (" + j.getFecha() + ")"));
        
        System.out.print("Ingrese el ID de la jornada: ");
        return Integer.parseInt(scanner.nextLine());
    }
    
    private int seleccionarVacuna() {
        List<Inventario> vacunas = controller.listarVacunasDisponibles();
        
        if (vacunas.isEmpty()) {
            System.out.println("No hay vacunas con stock disponible en el inventario.");
            return -1;
        }
        
        System.out.println("--- Seleccione la Vacuna a Aplicar ---");
        
        vacunas.forEach(v -> System.out.println("[" + v.getId() + "] " + v.getNombreProducto() + " | Stock: " + v.getCantidadStock()));
        
        System.out.print("Ingrese el ID de la vacuna: ");
        return Integer.parseInt(scanner.nextLine());
    }
}