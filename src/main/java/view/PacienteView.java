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
            System.out.println("\n--- Modulo de Gestion de Pacientes ---");
            System.out.println("\n--- Gestion de Dueños ---");
            System.out.println("1. Agregar Dueño");
            System.out.println("2. Buscar Dueño por Documento");
            System.out.println("3. Actualizar Dueño");
            System.out.println("4. Eliminar Dueño");
            System.out.println("5. Listar Todos los Dueños");
            System.out.println("\n--- Gestion de Mascotas ---");
            System.out.println("6. Agregar Mascota");
            System.out.println("7. Actualizar Mascota");
            System.out.println("8. Eliminar Mascota");
            System.out.println("9. Listar Todas las Mascotas");
            System.out.println("10. Ver Mascotas de un Dueño");
            System.out.println("11. Transferir Mascota entre Dueños");
            System.out.println("\n0. Volver al Menu Principal");

            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: agregarDueño(); break;
                case 2: buscarDueño(); break;
                case 3: actualizarDueño(); break;
                case 4: eliminarDueño(); break;
                case 5: listarDueños(); break;
                case 6: agregarMascota(); break;
                case 7: actualizarMascota(); break;
                case 8: eliminarMascota(); break;
                case 9: listarTodasLasMascotas(); break;
                case 10: verMascotasDeDueño(); break;
                case 11: transferirMascota(); break;
                case 0: System.out.println("Volviendo..."); break;
                default: System.out.println("Opción no válida.");
            }
        }
    }



    private void buscarDueño() {
        System.out.println("\n--- Buscar Dueño por Documento ---");
        System.out.print("Ingrese el numero de documento del dueño: ");
        String doc = scanner.nextLine();
        Dueño dueño = controller.buscarDueñoPorDocumento(doc);

        if (dueño != null) {
            System.out.println("Dueño encontrado:");
            System.out.println(dueño.toString());
        } else {
            System.out.println("No se encontro ningun dueño con ese documento.");
        }
    }

    
    private void actualizarDueño() {
        System.out.println("\n--- Actualizar Información de Dueño ---");
        System.out.print("Ingrese el documento del dueño a modificar: ");
        String doc = scanner.nextLine();
        Dueño dueño = controller.buscarDueñoPorDocumento(doc);

        if (dueño == null) {
            System.out.println("Dueño no encontrado.");
            return;
        }

        System.out.println("Datos actuales: " + dueño.getNombre());
        System.out.print("Nuevo nombre completo (dejar en blanco para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) dueño.setNombre(nombre);

        System.out.print("Nueva direccion (dejar en blanco para no cambiar): ");
        String direccion = scanner.nextLine();
        if (!direccion.isEmpty()) dueño.setDireccion(direccion);

        System.out.print("Nuevo telefono (dejar en blanco para no cambiar): ");
        String telefono = scanner.nextLine();
        if (!telefono.isEmpty()) dueño.setTelefono(telefono);

        controller.actualizarDueño(dueño);
        System.out.println("¡Dueño actualizado correctamente!");
    }

    
    private void eliminarDueño() {
        System.out.println("\n--- Eliminar Dueño ---");
        System.out.print("Ingrese el documento del dueño a eliminar: ");
        String doc = scanner.nextLine();
        Dueño dueño = controller.buscarDueñoPorDocumento(doc);

        if (dueño == null) {
            System.out.println("Dueño no encontrado.");
            return;
        }

        System.out.println("Se eliminara al dueño: " + dueño.getNombre());
        System.out.print("¿Esta seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        
        if (confirmacion.equalsIgnoreCase("S")) {
            controller.eliminarDueño(dueño.getId());
            System.out.println("Dueño eliminado.");
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    
    private void actualizarMascota() {
        System.out.println("\n--- Actualizar Informacion de Mascota ---");
        System.out.print("Ingrese el ID de la mascota a modificar: ");
        int idMascota = scanner.nextInt();
        scanner.nextLine();
        Mascota mascota = controller.buscarMascotaPorId(idMascota);

        if (mascota == null) {
            System.out.println("Mascota no encontrada.");
            return;
        }

        System.out.println("Datos actuales: Nombre=" + mascota.getNombre() + ", Peso=" + mascota.getPesoActual());
        System.out.print("Nuevo nombre (dejar en blanco para no cambiar): ");
        String nombre = scanner.nextLine();
        if (!nombre.isEmpty()) mascota.setNombre(nombre);
        
        System.out.print("Nuevo peso (dejar en blanco para no cambiar): ");
        String pesoStr = scanner.nextLine();
        if (!pesoStr.isEmpty()) mascota.setPesoActual(Double.parseDouble(pesoStr));
        
        System.out.print("Nuevo microchip (dejar en blanco para no cambiar): ");
        String micro = scanner.nextLine();
        if (!micro.isEmpty()) mascota.setMicrochip(micro);
        
        System.out.print("Nuevo tatuaje (dejar en blanco para no cambiar): ");
        String tatuaje = scanner.nextLine();
        if (!tatuaje.isEmpty()) mascota.setTatuaje(tatuaje);
        
        System.out.print("Nuevo Url foto (dejar en blanco para no cambiar): ");
        String urlFoto = scanner.nextLine();
        if (!urlFoto.isEmpty()) mascota.setUrlFoto(urlFoto);
        
        System.out.print("Nuevo alergia (dejar en blanco para no cambiar): ");
        String alergia = scanner.nextLine();
        if (!alergia.isEmpty()) mascota.setAlergias(alergia);
        
        controller.actualizarMascota(mascota);
        System.out.println("¡Mascota actualizada correctamente!");
    }

    
    private void eliminarMascota() {
        System.out.println("\n--- Eliminar Mascota ---");
        System.out.print("Ingrese el ID de la mascota a eliminar: ");
        int idMascota = scanner.nextInt();
        scanner.nextLine();
        Mascota mascota = controller.buscarMascotaPorId(idMascota);

        if (mascota == null) {
            System.out.println("Mascota no encontrada.");
            return;
        }
        
        System.out.println("Se eliminara a la mascota: " + mascota.getNombre());
        System.out.print("¿Esta seguro? (S/N): ");
        String confirmacion = scanner.nextLine();
        if (confirmacion.equalsIgnoreCase("S")) {
            controller.eliminarMascota(mascota.getId());
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    
    private void listarTodasLasMascotas() {
        System.out.println("\n--- Listado de Todas las Mascotas ---");
        List<Mascota> mascotas = controller.listarTodasLasMascotas();
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas en el sistema.");
        } else {
            mascotas.forEach(m -> System.out.println("ID: " + m.getId() + " | Nombre: " + m.getNombre() + " | Dueño ID: " + m.getDueñoId()));
        }
    }

    private void transferirMascota() {
        System.out.println("\n--- Transferencia de Mascota ---");
        System.out.print("Ingrese el documento del DUEÑO ACTUAL: ");
        String docActual = scanner.nextLine();
        Dueño dueñoActual = controller.buscarDueñoPorDocumento(docActual);
        if (dueñoActual == null) {
            System.out.println("Dueño actual no encontrado.");
            return;
        }

        List<Mascota> mascotas = controller.listarMascotasPorDueño(dueñoActual.getId());
        if (mascotas.isEmpty()) {
            System.out.println("Este dueño no tiene mascotas para transferir.");
            return;
        }

        System.out.println("Mascotas de " + dueñoActual.getNombre() + ":");
        mascotas.forEach(m -> System.out.println("  ID: " + m.getId() + " | Nombre: " + m.getNombre()));
        
        System.out.print("Ingrese el ID de la mascota a transferir: ");
        int idMascota = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Ingrese el documento del NUEVO DUEÑO: ");
        String docNuevo = scanner.nextLine();

        System.out.println("\nResumen de la transferencia:");
        System.out.println("Mascota ID: " + idMascota);
        System.out.println("De Dueño: " + docActual);
        System.out.println("A Dueño: " + docNuevo);
        System.out.print("¿Confirma la operación? (S/N): ");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            String resultado = controller.transferirMascota(idMascota, docActual, docNuevo);
            System.out.println(resultado);
        } else {
            System.out.println("Transferencia cancelada.");
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
        
        System.out.print("Microchip (Si no posee colocar el documento del dueño): ");
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
            mascotas.forEach(m -> System.out.println(m.toString()));
        }
    }
    
}