package Ejercicio3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaProcesos lista = new ListaProcesos();
        final int QUANTUM = 2;

        System.out.print("Cuantos procesos desea registrar: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.print("Nombre del proceso " + (i + 1) + ": ");
            String nombre = sc.nextLine();
            System.out.print("Tiempo requerido de " + nombre + ": ");
            int tiempo = Integer.parseInt(sc.nextLine());
            lista.agregar(nombre, tiempo);
        }

        System.out.println("\nEstado inicial:");
        lista.mostrar();

        System.out.println("\n--- SIMULACION ROUND-ROBIN (quantum = " + QUANTUM + ") ---");
        lista.ejecutarRoundRobin(QUANTUM);

        sc.close();
    }
}
