package view;

import controller.PacienteController;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import model.entities.Dueño;
import model.entities.Especie;
import model.entities.Mascota;
import model.entities.Raza;
import model.enums.Sexo;

public class PacienteView {
    private PacienteController controller;
    private Scanner scanner;

    public PacienteView() {
        this.controller = new PacienteController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Módulo de Gestión de Pacientes ---");
            System.out.println("1. Agregar Nuevo Dueño");
            System.out.println("2. Agregar Nueva Mascota");
            System.out.println("3. Listar todos los Dueños");
            System.out.println("4. Ver mascotas de un Dueño");
            System.out.println("0. Volver al Menú Principal");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1: agregarDueño(); break;
                case 2: agregarMascota(); break;
                case 3: listarDueños(); break;
                case 4: verMascotasDeDueño(); break;
                case 0: System.out.println("Volviendo al menú principal..."); break;
                default: System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private void agregarDueño() {
        System.out.println("\n--- Registro de Nuevo Dueño ---");
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Número de documento: ");
        String documento = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Contacto de Emergencia: ");
        String contactoEmergencia = scanner.nextLine();
        Date fechaRegistro = new Date(System.currentTimeMillis());

        Dueño nuevoDueño = new Dueño(nombre, documento, direccion, telefono, email, contactoEmergencia, fechaRegistro, true);
        controller.agregarDueño(nuevoDueño);
        System.out.println("¡Dueño registrado exitosamente con ID: " + nuevoDueño.getId() + "!");
    }

    private void agregarMascota() {
        System.out.println("\n--- Registro de Nueva Mascota ---");
        System.out.print("Ingrese el número de documento del dueño: ");
        String docDueño = scanner.nextLine();
        
        Dueño dueño = controller.buscarDueñoPorDocumento(docDueño);
        if (dueño == null) {
            System.out.println("Error: No se encontró un dueño con ese documento.");
            return;
        }

        System.out.println("Dueño encontrado: " + dueño.getNombre());
        int dueñoId = dueño.getId();
        
        System.out.print("Nombre de la mascota: ");
        String nombreMascota = scanner.nextLine();
        
        System.out.println("\n-- Seleccione la Especie --");
        List<Especie> especies = controller.listarEspecies();
        especies.forEach(e -> System.out.println("[" + e.getId() + "] " + e.getNombre()));
        System.out.print("Ingrese el ID de la especie: ");
        int especieId = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("\n-- Seleccione la Raza --");
        List<Raza> razas = controller.listarRazasPorEspecie(especieId);
        razas.forEach(r -> System.out.println("[" + r.getId() + "] " + r.getNombre()));
        System.out.print("Ingrese el ID de la raza: ");
        int razaId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
        Date fechaNac = Date.valueOf(scanner.nextLine());
        System.out.print("Sexo (Macho/Hembra): ");
        String sexoStr = scanner.nextLine();
        Sexo sexo = sexoStr.equalsIgnoreCase("Macho") ? Sexo.MACHO : Sexo.HEMBRA;
        System.out.print("Peso actual (kg): ");
        double peso = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Microchip (opcional): ");
        String microchip = scanner.nextLine();
        System.out.print("Tatuaje (opcional): ");
        String tatuaje = scanner.nextLine();
        System.out.print("URL de foto (opcional): ");
        String urlFoto = scanner.nextLine();
        System.out.print("Alergias conocidas: ");
        String alergias = scanner.nextLine();
        System.out.print("Condiciones preexistentes: ");
        String condiciones = scanner.nextLine();
        Date fechaRegistro = new Date(System.currentTimeMillis());

        Mascota nuevaMascota = new Mascota(dueñoId, nombreMascota, razaId, fechaNac, sexo, peso, microchip, tatuaje, urlFoto, alergias, condiciones, fechaRegistro, true);
        controller.agregarMascota(nuevaMascota);
    }

    private void listarDueños() {
        System.out.println("\n--- Listado de Dueños Registrados ---");
        List<Dueño> dueños = controller.listarDueños();
        if (dueños.isEmpty()) {
            System.out.println("No hay dueños registrados.");
        } else {
            dueños.forEach(d -> System.out.println("ID: " + d.getId() + " | Nombre: " + d.getNombre() + " | Documento: " + d.getNumeroDocumento()));
        }
    }
    
    private void verMascotasDeDueño() {
        System.out.println("\n--- Consultar Mascotas por Dueño ---");
        System.out.print("Ingrese el número de documento del dueño: ");
        String docDueño = scanner.nextLine();
        
        Dueño dueño = controller.buscarDueñoPorDocumento(docDueño);
        if (dueño == null) {
            System.out.println("Error: No se encontró un dueño con ese documento.");
            return;
        }

        System.out.println("Mascotas de: " + dueño.getNombre());
        List<Mascota> mascotas = controller.listarMascotasPorDueño(dueño.getId());
        if (mascotas.isEmpty()) {
            System.out.println("Este dueño no tiene mascotas registradas.");
        } else {
            mascotas.forEach(m -> System.out.println("  -> ID: " + m.getId() + " | Nombre: " + m.getNombre() + " | Sexo: " + m.getSexo()));
        }
    }
}