package Ejercicio1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaCircular lista = new ListaCircular();
        int opcion;

        do {
            System.out.println("\n--- LISTA CIRCULAR ---");
            System.out.println("1. Insertar al inicio");
            System.out.println("2. Insertar al final");
            System.out.println("3. Mostrar elementos");
            System.out.println("4. Verificar si esta vacia");
            System.out.println("5. Contar elementos");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Dato a insertar al inicio: ");
                    lista.insertarAlInicio(Integer.parseInt(sc.nextLine()));
                    break;
                case 2:
                    System.out.print("Dato a insertar al final: ");
                    lista.insertarAlFinal(Integer.parseInt(sc.nextLine()));
                    break;
                case 3:
                    lista.mostrar();
                    break;
                case 4:
                    System.out.println("¿Esta vacia? " + lista.estaVacia());
                    break;
                case 5:
                    System.out.println("Elementos: " + lista.contar());
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
