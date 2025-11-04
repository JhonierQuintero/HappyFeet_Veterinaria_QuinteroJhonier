package view;

import controller.CitasController;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import model.entities.Cita;
import model.entities.CitaEstado;
import model.entities.ConsultaMedica;
import model.entities.HistorialMedico;
import model.entities.Inventario;
import model.entities.Mascota;
import model.entities.Prescripcion;
import model.entities.ProcedimientoEspecial;
import model.entities.Veterinario;
import model.enums.EstadoProcedimiento;

public class CitasView {
    private CitasController controller;
    private Scanner scanner;

    public CitasView() {
        this.controller = new CitasController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Modulo de Servicios Medicos ---");
            System.out.println("--- Gestion de Citas y Consultas ---");
            System.out.println("1. Agendar Nueva Cita");
            System.out.println("2. Registrar Consulta Medica con Prescripcion");
            System.out.println("3. Registrar Procedimiento Especial");
            System.out.println("4. Ver Historial Medico de una Mascota");
            System.out.println("5. Listar Todas las Citas");
            System.out.println("6. Cambiar Estado de una Cita");
            System.out.println("--- Gestion de Veterinarios ---");
            System.out.println("7. Agregar Nuevo Veterinario");
            System.out.println("8. Listar Veterinarios");
            System.out.println("9. Actualizar Veterinario");
            System.out.println("10. Eliminar Veterinario");
            System.out.println("0. Volver al Menu Principal");

            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine()); 

            switch (opcion) {
                case 1: agendarNuevaCita(); break;
                case 2: registrarNuevaConsulta(); break;
                case 3: registrarNuevoProcedimiento(); break;
                case 4: verHistorialMedico(); break;
                case 5: listarCitas(); break;
                case 6: cambiarEstadoCita(); break;
                case 7: agregarNuevoVeterinario(); break;
                case 8: listarVeterinarios(); break;
                case 9: actualizarVeterinario(); break;
                case 10: eliminarVeterinario(); break;
                case 0: System.out.println("Volviendo..."); break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private void agendarNuevaCita() {
        System.out.println("\n--- Agendar Nueva Cita ---");
        int mascotaId = seleccionarMascota();
        if (mascotaId == -1) return;

        int vetId = seleccionarVeterinario();
        if (vetId == -1) return;
        
        System.out.print("Fecha y Hora de la cita (formato AAAA-MM-DD HH:MM:SS): ");
        Timestamp fechaHora = Timestamp.valueOf(scanner.nextLine());
        
        System.out.print("Motivo de la cita: ");
        String motivo = scanner.nextLine();
        
        System.out.print("Observaciones (opcional): ");
        String observaciones = scanner.nextLine();

        int estadoId = 1;
        
        Cita nuevaCita = new Cita(0, mascotaId, vetId, fechaHora, motivo, estadoId, observaciones);
        controller.agendarCita(nuevaCita);
        
        if (nuevaCita.getId() > 0) {
            System.out.println("Cita agendada con exito con ID: " + nuevaCita.getId());
        } else {
            System.out.println("Hubo un error al agendar la cita.");
        }
    }

    private void registrarNuevaConsulta() {
        System.out.println("\n--- Registrar Nueva Consulta Medica ---");
        int mascotaId = seleccionarMascota();
        if (mascotaId == -1) return;

        int vetId = seleccionarVeterinario();
        if (vetId == -1) return;

        Timestamp fechaHora = new Timestamp(System.currentTimeMillis());
        System.out.print("Motivo de la visita: ");
        String motivo = scanner.nextLine();
        System.out.print("Sintomas observados: ");
        String sintomas = scanner.nextLine();
        System.out.print("Diagnostico del veterinario: ");
        String diagnostico = scanner.nextLine();
        System.out.print("Recomendaciones: ");
        String recomendaciones = scanner.nextLine();
        System.out.print("Peso registrado (kg): ");
        double peso = Double.parseDouble(scanner.nextLine());
        System.out.print("Temperatura (C): ");
        double temp = Double.parseDouble(scanner.nextLine());
        
        ConsultaMedica nuevaConsulta = new ConsultaMedica(0, mascotaId, vetId, null, fechaHora, motivo, sintomas, diagnostico, recomendaciones, peso, temp);
        controller.registrarConsulta(nuevaConsulta);

        if (nuevaConsulta.getId() > 0) {
            System.out.println("Consulta registrada con exito con ID: " + nuevaConsulta.getId());
            System.out.print("Desea agregar una prescripcion para esta consulta? (S/N): ");
            String resp = scanner.nextLine();
            if (resp.equalsIgnoreCase("S")) {
                gestionarPrescripciones(nuevaConsulta.getId());
            }
        } else {
            System.out.println("Hubo un error al registrar la consulta.");
        }
    }
    
    private void gestionarPrescripciones(int consultaId) {
        String continuar = "S";
        while (continuar.equalsIgnoreCase("S")) {
            System.out.println("\n--- Nueva Prescripcion ---");
            List<Inventario> productos = controller.listarProductosDeInventario();
            if (productos.isEmpty()) {
                System.out.println("No hay productos en el inventario para prescribir.");
                return;
            }
            
            productos.forEach(p -> System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombreProducto() + " | Stock: " + p.getCantidadStock()));
            System.out.print("Ingrese el ID del producto a prescribir: ");
            int prodId = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Cantidad a prescribir: ");
            int cantidad = Integer.parseInt(scanner.nextLine());
            System.out.print("Dosis (ej. 1 tableta, 5 ml): ");
            String dosis = scanner.nextLine();
            System.out.print("Frecuencia (ej. cada 8 horas): ");
            String frec = scanner.nextLine();
            System.out.print("Duracion en dias: ");
            int dias = Integer.parseInt(scanner.nextLine());
            System.out.print("Instrucciones adicionales: ");
            String inst = scanner.nextLine();
            
            Prescripcion nuevaPrescripcion = new Prescripcion(consultaId, null, prodId, cantidad, dosis, frec, dias, inst, new Timestamp(System.currentTimeMillis()));
            String resultado = controller.crearPrescripcion(nuevaPrescripcion);
            System.out.println(resultado);
            
            if (resultado.startsWith("Error")) {
                System.out.println("La prescripcion no fue guardada. Intente de nuevo.");
            }
            
            System.out.print("Desea agregar otra prescripcion? (S/N): ");
            continuar = scanner.nextLine();
        }
    }

    private void registrarNuevoProcedimiento() {
        System.out.println("\n--- Registrar Procedimiento Especial ---");
        int mascotaId = seleccionarMascota();
        if (mascotaId == -1) return;

        int vetId = seleccionarVeterinario();
        if (vetId == -1) return;

        System.out.print("Nombre del procedimiento (Ej. Cirugia de esterilizacion): ");
        String nombreProc = scanner.nextLine();
        System.out.print("Fecha y Hora del procedimiento (AAAA-MM-DD HH:MM:SS): ");
        Timestamp fechaHora = Timestamp.valueOf(scanner.nextLine());
        System.out.print("Informacion preoperatoria: ");
        String infoPre = scanner.nextLine();
        System.out.print("Detalle del procedimiento: ");
        String detalle = scanner.nextLine();
        System.out.print("Seguimiento postoperatorio: ");
        String seguimiento = scanner.nextLine();
        System.out.print("Costo del procedimiento: ");
        double costo = Double.parseDouble(scanner.nextLine());

        ProcedimientoEspecial nuevoProc = new ProcedimientoEspecial(mascotaId, vetId, nombreProc, fechaHora, infoPre, detalle, seguimiento, EstadoProcedimiento.PROGRAMADA, costo);
        controller.registrarProcedimiento(nuevoProc);

        System.out.println("Procedimiento registrado con exito con ID: " + nuevoProc.getId());
    }

    private void verHistorialMedico() {
        System.out.println("\n--- Ver Historial Medico ---");
        int mascotaId = seleccionarMascota();
        if (mascotaId == -1) return;
        
        Mascota mascota = controller.buscarMascotaPorId(mascotaId);
        System.out.println("Historial de: " + mascota.getNombre());

        List<HistorialMedico> historial = controller.verHistorialDeMascota(mascotaId);
        if (historial.isEmpty()) {
            System.out.println("Esta mascota no tiene historial medico registrado.");
        } else {
            historial.forEach(h -> System.out.println(h.getFechaEvento() + " - " + h.getDescripcion()));
        }
    }

    private void listarCitas() {
        System.out.println("\n--- Listado de Todas las Citas ---");
        List<Cita> citas = controller.listarCitas();
        if (citas.isEmpty()) {
            System.out.println("No hay citas registradas.");
        } else {
            citas.forEach(c -> System.out.println("ID: " + c.getId() + " | Mascota ID: " + c.getMascotaId() + " | Fecha: " + c.getFechaHora() + " | Motivo: " + c.getMotivo()));
        }
    }

    private void cambiarEstadoCita() {
        System.out.println("\n--- Cambiar Estado de una Cita ---");
        System.out.print("Ingrese el ID de la cita a modificar: ");
        int citaId = Integer.parseInt(scanner.nextLine());

        Cita cita = controller.buscarCitaPorId(citaId);
        if (cita == null) {
            System.out.println("No se encontro una cita con ese ID.");
            return;
        }

        int nuevoEstadoId = seleccionarEstadoCita();
        if (nuevoEstadoId == -1) return;

        cita.setEstadoId(nuevoEstadoId);
        controller.actualizarCita(cita);
        System.out.println("Estado de la cita actualizado con exito!");
    }

    private void agregarNuevoVeterinario() {
        System.out.println("\n--- Registrar Nuevo Veterinario ---");
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("Documento de identidad: ");
        String doc = scanner.nextLine();
        System.out.print("Licencia profesional: ");
        String lic = scanner.nextLine();
        System.out.print("Especialidad: ");
        String esp = scanner.nextLine();
        System.out.print("Telefono: ");
        String tel = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Fecha de contratacion (AAAA-MM-DD): ");
        Date fecha = Date.valueOf(scanner.nextLine());

        Veterinario vet = new Veterinario(nombre, doc, lic, esp, tel, email, fecha, true);
        controller.agregarVeterinario(vet);
        System.out.println("Veterinario registrado con exito con ID: " + vet.getId());
    }

    private void listarVeterinarios() {
        System.out.println("\n--- Listado de Veterinarios ---");
        List<Veterinario> veterinarios = controller.listarVeterinarios();
        if (veterinarios.isEmpty()) {
            System.out.println("No hay veterinarios registrados.");
        } else {
            veterinarios.forEach(v -> System.out.println("ID: " + v.getId() + " | Nombre: " + v.getNombreCompleto() + " | Especialidad: " + v.getEspecialidad()));
        }
    }

    private void actualizarVeterinario() {
        System.out.println("\n--- Actualizar Veterinario ---");
        int vetId = seleccionarVeterinario();
        if (vetId == -1) return;
        
        Veterinario vet = controller.buscarVeterinarioPorId(vetId);
        
        System.out.print("Nuevo telefono (actual: " + vet.getTelefono() + "): ");
        String tel = scanner.nextLine();
        if (!tel.isEmpty()) vet.setTelefono(tel);

        System.out.print("Nuevo email (actual: " + vet.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) vet.setEmail(email);

        controller.actualizarVeterinario(vet);
        System.out.println("Veterinario actualizado con exito.");
    }
    
    private void eliminarVeterinario() {
        System.out.println("\n--- Eliminar Veterinario ---");
        int vetId = seleccionarVeterinario();
        if (vetId == -1) return;
        
        System.out.print("Esta seguro de que desea eliminar a este veterinario? (S/N): ");
        String conf = scanner.nextLine();
        if (conf.equalsIgnoreCase("S")) {
            controller.eliminarVeterinario(vetId);
            System.out.println("Veterinario eliminado.");
        } else {
            System.out.println("Operacion cancelada.");
        }
    }
    
    private int seleccionarMascota() {
        List<Mascota> mascotas = controller.listarMascotas();
        if (mascotas.isEmpty()) {
            System.out.println("No hay mascotas registradas. Agregue una primero.");
            return -1;
        }
        System.out.println("--- Seleccione una Mascota ---");
        mascotas.forEach(m -> System.out.println("[" + m.getId() + "] " + m.getNombre()));
        System.out.print("Ingrese el ID de la mascota: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        if (!controller.existeMascota(id)) {
            System.out.println("Error: El ID de la mascota no existe. Operacion cancelada.");
            return -1;
        }
        return id;
    }

    private int seleccionarVeterinario() {
        List<Veterinario> veterinarios = controller.listarVeterinarios();
        if (veterinarios.isEmpty()) {
            System.out.println("No hay veterinarios registrados.");
            return -1;
        }
        System.out.println("--- Seleccione un Veterinario ---");
        veterinarios.forEach(v -> System.out.println("[" + v.getId() + "] " + v.getNombreCompleto()));
        System.out.print("Ingrese el ID del veterinario: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        if (!controller.existeVeterinario(id)) {
            System.out.println("Error: El ID del veterinario no existe. Operacion cancelada.");
            return -1;
        }
        return id;
    }

    private int seleccionarEstadoCita() {
        List<CitaEstado> estados = controller.listarEstadosDeCita();
        if (estados.isEmpty()) {
            System.out.println("No hay estados de cita configurados.");
            return -1;
        }
        System.out.println("--- Seleccione un Nuevo Estado ---");
        estados.forEach(e -> System.out.println("[" + e.getId() + "] " + e.getNombre() + " - " + e.getDescripcion()));
        System.out.print("Ingrese el ID del nuevo estado: ");
        return Integer.parseInt(scanner.nextLine());
    }
}