package view;

import controller.InventarioController;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;
import model.entities.Inventario;
import model.entities.ProductoTipo;
import model.entities.Proveedor;

public class InventarioView {
    private InventarioController controller;
    private Scanner scanner;

    public InventarioView() {
        this.controller = new InventarioController();
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n--- Modulo de Inventario y Farmacia ---");
            System.out.println("\n1. Agregar Nuevo Producto al Inventario");
            System.out.println("2. Listar Todos los Productos");
            System.out.println("3. Ajustar Stock de un Producto");
            System.out.println("4. Ver Productos con Stock Bajo");
            System.out.println("5. Ver Productos Proximos a Vencer");
            System.out.println("6. Agregar Nuevo Proveedor");
            System.out.println("7. Listar Todos los Proveedores");
            System.out.println("8. Actualizar Proveedor");
            System.out.println("9. Eliminar Proveedor");
            System.out.println("0. Volver al Menu Principal");

            System.out.print("Seleccione una opcion: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1: agregarProducto(); break;
                case 2: listarProductos(); break;
                case 3: ajustarStock(); break;
                case 4: verProductosConStockBajo(); break;
                case 5: verProductosProximosAVencer(); break;
                case 6: agregarProveedor(); break;
                case 7: listarProveedores(); break;
                case 8: actualizarProveedor(); break;
                case 9: eliminarProveedor(); break;
                case 0: System.out.println("Volviendo..."); break;
                default: System.out.println("Opcion no valida.");
            }
        }
    }

    private void agregarProducto() {
        System.out.println("\n--- Agregar Nuevo Producto ---");
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();
        
        int tipoId = seleccionarTipoProducto();
        if (tipoId == -1) return;

        System.out.print("Descripcion: ");
        String desc = scanner.nextLine();
        System.out.print("Fabricante: ");
        String fab = scanner.nextLine();
        
        Integer provId = seleccionarProveedor();
        
        System.out.print("Lote: ");
        String lote = scanner.nextLine();
        
        System.out.print("Cantidad inicial en stock: ");
        int stock = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Stock minimo para alerta: ");
        int stockMin = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Unidad de medida (ej. unidad, ml, caja): ");
        String unidad = scanner.nextLine();
        
        System.out.print("Fecha de vencimiento (AAAA-MM-DD): ");
        Date fechaVenc = Date.valueOf(scanner.nextLine());
        
        System.out.print("Precio de compra: ");
        double precioCompra = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Precio de venta: ");
        double precioVenta = Double.parseDouble(scanner.nextLine());
        
        System.out.print("Requiere receta? (S/N): ");
        boolean receta = scanner.nextLine().equalsIgnoreCase("S");

        Inventario nuevoProd = new Inventario(nombre, tipoId, desc, fab, provId, lote, stock, stockMin, unidad, fechaVenc, precioCompra, precioVenta, receta, true);
        controller.agregarProducto(nuevoProd);
        System.out.println("Producto agregado con exito con ID: " + nuevoProd.getId());
    }

    private void listarProductos() {
        System.out.println("\n--- Listado de Productos en Inventario ---");
        List<Inventario> productos = controller.listarProductos();
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
        } else {
            productos.forEach(p -> System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombreProducto() + " | Stock: " + p.getCantidadStock() + " | Precio Venta: " + p.getPrecioVenta()));
        }
    }

    private void ajustarStock() {
        System.out.println("\n--- Ajustar Stock de un Producto ---");
        int prodId = seleccionarProducto();
        if (prodId == -1) return;
        
        System.out.print("Ingrese la nueva cantidad total en stock: ");
        int nuevaCantidad = Integer.parseInt(scanner.nextLine());
        
        System.out.print("Motivo del ajuste (ej. Conteo fisico, Devolucion): ");
        String motivo = scanner.nextLine();
        
        String resultado = controller.ajustarStock(prodId, nuevaCantidad, motivo);
        System.out.println(resultado);
    }
    
    private void verProductosConStockBajo() {
        System.out.println("\n--- Alerta: Productos con Stock Bajo ---");
        List<Inventario> productos = controller.obtenerProductosConStockBajo();
        
        if (productos.isEmpty()) {
            System.out.println("No hay productos por debajo del stock minimo.");
        } else {
            productos.forEach(p -> System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombreProducto() + " | Stock Actual: " + p.getCantidadStock() + " | Stock Minimo: " + p.getStockMinimo()));
        }
    }

    private void verProductosProximosAVencer() {
        System.out.println("\n--- Alerta: Productos Proximos a Vencer ---");
        System.out.print("Mostrar productos que vencen en los proximos (dias): ");
        int dias = Integer.parseInt(scanner.nextLine());
        List<Inventario> productos = controller.obtenerProductosProximosAVencer(dias);

        if (productos.isEmpty()) {
            System.out.println("No hay productos proximos a vencer en ese rango de dias.");
        } else {
            productos.forEach(p -> System.out.println("ID: " + p.getId() + " | Nombre: " + p.getNombreProducto() + " | Fecha de Vencimiento: " + p.getFechaVencimiento()));
        }
    }
    
    private void agregarProveedor() {
        System.out.println("\n--- Agregar Nuevo Proveedor ---");
        System.out.print("Nombre de la empresa: ");        
        String nombre = scanner.nextLine();
        
        System.out.print("Nombre del contacto: ");        
        String contacto = scanner.nextLine();
        
        System.out.print("Telefono: ");        
        String tel = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Direccion: ");
        String dir = scanner.nextLine();

        Proveedor nuevoProv = new Proveedor(nombre, contacto, tel, email, dir, true, new Timestamp(System.currentTimeMillis()));
        controller.agregarProveedor(nuevoProv);
        System.out.println("Proveedor agregado con exito con ID: " + nuevoProv.getId());
    }

    private void listarProveedores() {
        System.out.println("\n--- Listado de Proveedores ---");
        List<Proveedor> proveedores = controller.listarProveedores();
        
        if (proveedores.isEmpty()) {
            System.out.println("No hay proveedores registrados.");
        } else {
            proveedores.forEach(p -> System.out.println("ID: " + p.getId() + " | Empresa: " + p.getNombreEmpresa() + " | Telefono: " + p.getTelefono()));
        }
    }

    private void actualizarProveedor() {
        System.out.println("\n--- Actualizar Proveedor ---");
        Integer provId = seleccionarProveedor();
        if (provId == null) return;
        
        Proveedor prov = controller.buscarProveedorPorId(provId);
        
        System.out.print("Nuevo nombre de contacto (actual: " + prov.getContacto() + "): ");
        String contacto = scanner.nextLine();
        if (!contacto.isEmpty()) prov.setContacto(contacto);

        System.out.print("Nuevo telefono (actual: " + prov.getTelefono() + "): ");
        String tel = scanner.nextLine();
        if (!tel.isEmpty()) prov.setTelefono(tel);
        
        System.out.print("Nuevo email (actual: " + prov.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) prov.setEmail(email);

        controller.actualizarProveedor(prov);
        System.out.println("Proveedor actualizado con exito.");
    }
    
    private void eliminarProveedor() {
        System.out.println("\n--- Eliminar Proveedor ---");
        Integer provId = seleccionarProveedor();
        if (provId == null) return;
        
        System.out.print("Esta seguro de que desea eliminar a este proveedor? (S/N): ");
        String conf = scanner.nextLine();
        if (conf.equalsIgnoreCase("S")) {
            controller.eliminarProveedor(provId);
            System.out.println("Proveedor eliminado.");
        } else {
            System.out.println("Operacion cancelada.");
        }
    }

    private int seleccionarTipoProducto() {
        List<ProductoTipo> tipos = controller.listarTiposDeProducto();
        if (tipos.isEmpty()){
            System.out.println("No hay tipos de producto configurados. No se puede continuar.");
            return -1;
        }
        System.out.println("--- Seleccione el Tipo de Producto ---");
        tipos.forEach(t -> System.out.println("[" + t.getId() + "] " + t.getNombre()));
        System.out.print("Ingrese el ID del tipo: ");
        return Integer.parseInt(scanner.nextLine());
    }

    private Integer seleccionarProveedor() {
        List<Proveedor> proveedores = controller.listarProveedores();
        
        if (proveedores.isEmpty()){
            System.out.println("No hay proveedores registrados. El producto se guardara sin proveedor.");
            return null;
        }
        System.out.println("--- Seleccione un Proveedor (o presione Enter para omitir) ---");
        
        proveedores.forEach(p -> System.out.println("[" + p.getId() + "] " + p.getNombreEmpresa()));
        
        System.out.print("Ingrese el ID del proveedor: ");
        String idStr = scanner.nextLine();
        return idStr.isEmpty() ? null : Integer.parseInt(idStr);
    }
    
    private int seleccionarProducto() {
        List<Inventario> productos = controller.listarProductos();
        
        
        if (productos.isEmpty()){
            System.out.println("No hay productos registrados. No se puede continuar.");
            return -1;
        }
        System.out.println("--- Seleccione un Producto ---");
        
        productos.forEach(p -> System.out.println("[" + p.getId() + "] " + p.getNombreProducto()));
        
        System.out.print("Ingrese el ID del producto: ");
        return Integer.parseInt(scanner.nextLine());
    }
}