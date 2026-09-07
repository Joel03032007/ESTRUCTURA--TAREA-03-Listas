package Ejercicio2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListaCircular lista = new ListaCircular();
        int opcion;

        do {
            System.out.println("\n--- LISTA CIRCULAR ---");
            System.out.println("1. Insertar en posicion");
            System.out.println("2. Eliminar por posicion");
            System.out.println("3. Eliminar por valor");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Dato: ");
                    int dato = Integer.parseInt(sc.nextLine());
                    System.out.print("Posicion (0.." + lista.contar() + "): ");
                    int pos = Integer.parseInt(sc.nextLine());
                    System.out.println("Antes de insertar:");
                    lista.mostrar();
                    lista.insertarEnPosicion(dato, pos);
                    System.out.println("Despues de insertar:");
                    lista.mostrar();
                    break;
                case 2:
                    System.out.print("Posicion a eliminar: ");
                    int posEliminar = Integer.parseInt(sc.nextLine());
                    System.out.println("Antes de eliminar:");
                    lista.mostrar();
                    lista.eliminarPorPosicion(posEliminar);
                    System.out.println("Despues de eliminar:");
                    lista.mostrar();
                    break;
                case 3:
                    System.out.print("Valor a eliminar: ");
                    int valor = Integer.parseInt(sc.nextLine());
                    System.out.println("Antes de eliminar:");
                    lista.mostrar();
                    lista.eliminarPorValor(valor);
                    System.out.println("Despues de eliminar:");
                    lista.mostrar();
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
