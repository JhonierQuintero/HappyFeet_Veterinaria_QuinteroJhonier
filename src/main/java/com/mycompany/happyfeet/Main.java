package com.mycompany.happyfeet;

import java.util.Scanner;
import view.PacienteView;
import view.CitasView;
import view.FacturacionView;
import view.InventarioView;
import view.ActividadesView;


public class Main {
    
    private PacienteView pacienteView;
    private CitasView citasView;
    private InventarioView inventarioView;
    private FacturacionView facturacionView;
    private ActividadesView actividadesView;
    private Scanner sc = new Scanner(System.in);
    
    
    public Main() {
        this.pacienteView = new PacienteView();
        this.citasView = new CitasView();
        this.inventarioView = new InventarioView();
        this.facturacionView = new FacturacionView();
        this.actividadesView = new ActividadesView();
    }

    public static void main(String[] args){
        Main main = new Main();
        main.mostrarMenuPrincipal();
    }
    
    public void mostrarMenuPrincipal() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=============================================");
            System.out.println("  SISTEMA DE GESTION VETERINARIA HAPPY FEET");
            System.out.println("=============================================");
            System.out.println("1. Modulo de Gestion de Pacientes");
            System.out.println("2. Modulo de Servicios Medicos y Citas");
            System.out.println("3. Modulo de Inventario y Farmacia");
            System.out.println("4. Modulo de Facturacion y Reportes");
            System.out.println("5. Modulo de Actividades Especiales");
            System.out.println("0. Salir del Sistema");
            System.out.print("Elija un modulo: ");
            
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    pacienteView.mostrarMenu();
                    break;
                case 2:
                    citasView.mostrarMenu();
                    break;
                case 3:
                    inventarioView.mostrarMenu();
                    break;
                case 4:
                    facturacionView.mostrarMenu();
                    break;
                case 5:
                    actividadesView.mostrarMenu();
                    break;
                case 0:
                    System.out.println("Gracias por usar Happy Feet. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }
}