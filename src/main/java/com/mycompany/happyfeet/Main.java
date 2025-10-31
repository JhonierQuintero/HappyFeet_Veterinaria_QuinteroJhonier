package com.mycompany.happyfeet;

import java.util.Scanner;
import view.PacienteView;


public class Main {
    
    private PacienteView pacienteView;
    private Scanner sc = new Scanner(System.in);
    
    
    public Main() {
        this.pacienteView = new PacienteView();
    }

    public static void main(String[] args){
        Main main = new Main();
        main.mostrarMenuPrincipal();
    }
    
    public void mostrarMenuPrincipal() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("\n=============================================");
            System.out.println("  SISTEMA DE GESTIÓN VETERINARIA HAPPY FEET");
            System.out.println("=============================================");
            System.out.println("1. Módulo de Gestión de Pacientes");
            System.out.println("2. Módulo de Servicios Médicos y Citas (No implementado)");
            System.out.println("3. Módulo de Inventario y Farmacia (No implementado)");
            System.out.println("4. Módulo de Facturación y Reportes (No implementado)");
            System.out.println("0. Salir del Sistema");
            System.out.print("Elija un modulo: ");
            
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    pacienteView.mostrarMenu();
                    break;
                case 2:
                case 3:
                case 4:
                    System.out.println("Este módulo aún no ha sido implementado.");
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