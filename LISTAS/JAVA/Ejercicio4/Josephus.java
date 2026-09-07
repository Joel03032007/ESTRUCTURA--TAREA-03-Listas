package Ejercicio4;

public class Josephus {

    private class Nodo {
        int persona;
        Nodo siguiente;
        Nodo(int persona) { this.persona = persona; }
    }

    // Arma el circulo de n personas (1..n) y elimina cada k-esima
    public void resolver(int n, int k) {
        Nodo cabeza = new Nodo(1);
        Nodo actual = cabeza;
        for (int i = 2; i <= n; i++) {          // arma el circulo con n personas
            actual.siguiente = new Nodo(i);
            actual = actual.siguiente;
        }
        actual.siguiente = cabeza;              // cierra el circulo (ultimo -> primero)

        System.out.println("n = " + n + ", k = " + k);
        System.out.print("Orden de eliminacion: ");

        Nodo previo = actual;                   // nodo anterior a cabeza (para poder eliminar)
        actual = cabeza;

        while (actual.siguiente != actual) {    // mientras queden mas de 1 persona
            for (int i = 1; i < k; i++) {        // avanza k-1 pasos contando personas
                previo = actual;
                actual = actual.siguiente;
            }
            System.out.print(actual.persona + " ");
            previo.siguiente = actual.siguiente; // saca a "actual" del circulo
            actual = previo.siguiente;           // el conteo sigue desde el siguiente
        }

        System.out.println("\nSobreviviente: " + actual.persona);
    }
}
