package Ejercicio4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Josephus j = new Josephus();

        System.out.print("Numero de personas (n): ");
        int n = Integer.parseInt(sc.nextLine());
        System.out.print("Cada cuantas personas se elimina (k): ");
        int k = Integer.parseInt(sc.nextLine());

        j.resolver(n, k);
        sc.close();
    }
}
