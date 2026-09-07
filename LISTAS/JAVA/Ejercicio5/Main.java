package Ejercicio5;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();
        int opcion;

        do {
            System.out.println("\n--- PLAYLIST CIRCULAR ---");
            System.out.println("1. Agregar cancion al inicio");
            System.out.println("2. Agregar cancion al final");
            System.out.println("3. Mostrar playlist");
            System.out.println("4. Reproducir siguiente");
            System.out.println("5. Eliminar cancion por nombre");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de la cancion: ");
                    playlist.agregarAlInicio(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Nombre de la cancion: ");
                    playlist.agregarAlFinal(sc.nextLine());
                    break;
                case 3:
                    playlist.mostrarPlaylist();
                    break;
                case 4:
                    playlist.reproducirSiguiente();
                    break;
                case 5:
                    System.out.print("Nombre de la cancion a eliminar: ");
                    playlist.eliminarPorNombre(sc.nextLine());
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
