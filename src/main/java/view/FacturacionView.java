package view;

import controller.FacturacionController;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.entities.Dueño;
import model.entities.Factura;
import model.entities.Inventario;
import model.entities.ItemFactura;
import model.entities.Servicio;
import model.enums.EstadoFactura;
import model.enums.MetodoPago;
import model.enums.TipoItemFactura;

public class FacturacionView {
    private FacturacionController controller;
    private Scanner scanner;
    private static final double IMPUESTO_IVA = 0.19;

    public FacturacionView() {
        this.controller = new FacturacionController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Modulo de Facturacion y Reportes ---");
            System.out.println("\n1. Generar Nueva Factura");
            System.out.println("2. Ver Reportes Gerenciales");
            System.out.println("3. Gestionar Servicios de la Clinica");
            System.out.println("0. Volver al Menu Principal");

            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1: generarNuevaFactura(); break;
                case 2: mostrarMenuReportes(); break;
                case 3: gestionarServicios(); break;
                case 0: System.out.println("Volviendo..."); break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private void generarNuevaFactura() {
        System.out.println("\n--- Generar Nueva Factura ---");
        System.out.print("Ingrese el documento del dueno: ");
        String doc = scanner.nextLine();
        
        Dueño dueño = controller.buscarDueñoPorDocumento(doc);
        
        if (dueño == null) {
            System.out.println("Error: Dueno no encontrado.");
            return;
        }
        System.out.println("Cliente: " + dueño.getNombre());
        
        List<ItemFactura> items = new ArrayList<>();
        double subtotalGeneral = 0;
        
        String agregarOtro = "S";
        
        while (agregarOtro.equalsIgnoreCase("S")) {
            System.out.print("Desea agregar un (1) Servicio o (2) Producto?: ");
            int tipo = Integer.parseInt(scanner.nextLine());
            
            if (tipo == 1) {
                ItemFactura item = agregarItemServicio();
                if (item != null) {
                    items.add(item);
                    subtotalGeneral += item.getSubtotal();
                }
            } else if (tipo == 2) {
                ItemFactura item = agregarItemProducto();
                if (item != null) {
                    items.add(item);
                    subtotalGeneral += item.getSubtotal();
                }
            } else {
                System.out.println("Opcion no valida.");
            }
            
            System.out.print("Desea agregar otro item a la factura? (S/N): ");
            agregarOtro = scanner.nextLine();
        }

        if (items.isEmpty()) {
            System.out.println("No se agregaron items. Factura cancelada.");
            return;
        }

        double impuesto = subtotalGeneral * IMPUESTO_IVA;
        double total = subtotalGeneral + impuesto;
        
        String numeroFactura = "FAC-" + dueño.getNumeroDocumento() + "-" + System.currentTimeMillis() % 10000;
        
        Factura factura = new Factura(dueño.getId(), numeroFactura, new Timestamp(System.currentTimeMillis()), subtotalGeneral, impuesto, 0, total, MetodoPago.EFECTIVO, EstadoFactura.PAGADA, "");
        controller.crearFacturaYGenerarTxt(factura, items);
    }
    
    private ItemFactura agregarItemServicio() {
        List<Servicio> servicios = controller.getServiciosActivos();
        
        if(servicios.isEmpty()) {
            System.out.println("No hay servicios activos para facturar. Por favor, agregue uno desde el menu de administracion.");
            return null;
        }
        System.out.println("--- Seleccione un Servicio ---");
        
        servicios.forEach(s -> System.out.println("ID: " + s.getId() + " | Nombre: " + s.getNombre() + " | Precio: " + s.getPrecioBase()));
        
        System.out.print("Ingrese el ID del servicio: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Servicio servicioSeleccionado = servicios.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
        
        if (servicioSeleccionado == null) {
            System.out.println("ID de servicio no valido.");
            return null;
        }
        
        return new ItemFactura(0, TipoItemFactura.SERVICIO, null, id, servicioSeleccionado.getNombre(), 1, servicioSeleccionado.getPrecioBase(), servicioSeleccionado.getPrecioBase());
    }

    private ItemFactura agregarItemProducto() {
        List<Inventario> productos = controller.getProductosActivos();
        
        if(productos.isEmpty()){
            System.out.println("No hay productos activos en el inventario.");
            return null;
        }
        
        System.out.println("--- Seleccione un Producto ---");
        
        productos.forEach(p -> System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombreProducto() + " | Precio: " + p.getPrecioVenta() + " | Stock: " + p.getCantidadStock()));
        
        System.out.print("Ingrese el ID del producto: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Inventario productoSeleccionado = controller.buscarProductoPorId(id);
        
        if (productoSeleccionado == null) {
            System.out.println("ID de producto no valido.");
            return null;
        }

        System.out.print("Cantidad a vender: ");
        int cantidad = Integer.parseInt(scanner.nextLine());

        if (cantidad > productoSeleccionado.getCantidadStock()) {
            System.out.println("No hay stock suficiente. Solo hay " + productoSeleccionado.getCantidadStock() + " unidades.");
            return null;
        }
        
        double subtotal = productoSeleccionado.getPrecioVenta() * cantidad;
        return new ItemFactura(0, TipoItemFactura.PRODUCTO, id, null, productoSeleccionado.getNombreProducto(), cantidad, productoSeleccionado.getPrecioVenta(), subtotal);
    }

    private void mostrarMenuReportes() {
        int opcion = -1;
        
        while (opcion != 0) {
            System.out.println("\n--- Menu de Reportes Gerenciales ---");
            System.out.println("1. Servicios mas solicitados");
            System.out.println("2. Analisis de facturacion por periodo");
            System.out.println("3. Reporte de inventario (Stock bajo)");
            System.out.println("0. Volver");

            System.out.print("Seleccione un reporte: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1: reporteServicios(); break;
                case 2: reporteFacturacion(); break;
                case 3: reporteInventario(); break;
                case 0: break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private void reporteServicios() {
        System.out.println("\n--- Reporte: Servicios Mas Solicitados ---");
        List<String> reporte = controller.generarReporteServiciosMasSolicitados();
        
        if (reporte.isEmpty()) {
            System.out.println("No hay datos de servicios para mostrar.");
        } else {
            reporte.forEach(System.out::println);
        }
    }

    private void reporteFacturacion() {
        System.out.println("\n--- Reporte: Analisis de Facturacion ---");
        System.out.print("Ingrese fecha de inicio (AAAA-MM-DD HH:MM:SS): ");
        Timestamp inicio = Timestamp.valueOf(scanner.nextLine());
        
        System.out.print("Ingrese fecha de fin (AAAA-MM-DD HH:MM:SS): ");
        Timestamp fin = Timestamp.valueOf(scanner.nextLine());
        
        String reporte = controller.generarReporteFacturacion(inicio, fin);
        System.out.println(reporte);
    }
    
    private void reporteInventario() {
        System.out.println("\n--- Reporte: Productos con Stock Bajo ---");
        List<Inventario> productos = controller.getProductosConStockBajo();
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos por debajo del stock minimo.");
        } else {
            productos.forEach(p -> System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombreProducto() + " | Stock Actual: " + p.getCantidadStock() + " | Stock Minimo: " + p.getStockMinimo()));
        }
    }
    
    private void gestionarServicios() {
        int opcion = -1;
        
        while (opcion != 0) {
            System.out.println("\n--- Administracion de Servicios ---");
            System.out.println("1. Agregar Nuevo Servicio");
            System.out.println("2. Listar Todos los Servicios");
            System.out.println("3. Actualizar un Servicio");
            System.out.println("4. Eliminar un Servicio");
            System.out.println("0. Volver al menu anterior");

            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1: agregarServicio(); break;
                case 2: listarServicios(); break;
                case 3: actualizarServicio(); break;
                case 4: eliminarServicio(); break;
                case 0: break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }
    
    private void agregarServicio() {
        System.out.println("\n--- Agregar Nuevo Servicio ---");
        System.out.print("Nombre del servicio: ");
        String nombre = scanner.nextLine();
        
        System.out.print("Descripcion: ");
        String desc = scanner.nextLine();
        
        System.out.print("Categoria (ej. Consulta, Cirugia, Vacunacion): ");
        String cat = scanner.nextLine();
        
        System.out.print("Precio base: ");
        double precio = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Duracion estimada en minutos: ");
        int duracion = Integer.parseInt(scanner.nextLine());
        
        Servicio nuevoServicio = new Servicio(nombre, desc, cat, precio, duracion, true);
        controller.agregarServicio(nuevoServicio);
        System.out.println("Servicio agregado con exito con ID: " + nuevoServicio.getId());
    }

    private void listarServicios() {
        System.out.println("\n--- Catalogo de Servicios ---");
        List<Servicio> servicios = controller.listarServicios();
        
        if (servicios.isEmpty()) {
            System.out.println("No hay servicios registrados.");
        } else {
            servicios.forEach(s -> System.out.println("ID: " + s.getId() + " | Nombre: " + s.getNombre() + " | Precio: " + s.getPrecioBase() + " | Activo: " + s.getActivo()));
        }
    }
    
    private void actualizarServicio() {
        System.out.println("\n--- Actualizar Servicio ---");
        System.out.print("Ingrese el ID del servicio a modificar: ");
        int id = Integer.parseInt(scanner.nextLine());
        Servicio servicio = controller.buscarServicioPorId(id);
        
        if (servicio == null) {
            System.out.println("Servicio no encontrado.");
            return;
        }

        System.out.print("Nuevo precio base (actual: " + servicio.getPrecioBase() + "): ");
        String precioStr = scanner.nextLine();
        
        if (!precioStr.isEmpty()) {
            servicio.setPrecioBase(Double.parseDouble(precioStr));
        }

        System.out.print("Esta activo? (S/N) (actual: " + (servicio.getActivo() ? "S" : "N") + "): ");
        String activoStr = scanner.nextLine();
        
        if (!activoStr.isEmpty()) {
            servicio.setActivo(activoStr.equalsIgnoreCase("S"));
        }
        
        controller.actualizarServicio(servicio);
        System.out.println("Servicio actualizado con exito.");
    }
    
    private void eliminarServicio() {
        System.out.println("\n--- Eliminar Servicio ---");
        System.out.print("Ingrese el ID del servicio a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        if (controller.buscarServicioPorId(id) == null) {
            System.out.println("Servicio no encontrado.");
            return;
        }
        
        System.out.print("Esta seguro de que desea eliminar este servicio? (S/N): ");
        String conf = scanner.nextLine();
        
        if (conf.equalsIgnoreCase("S")) {
            controller.eliminarServicio(id);
            System.out.println("Servicio eliminado.");
        } else {
            System.out.println("Operacion cancelada.");
        }
    }
}