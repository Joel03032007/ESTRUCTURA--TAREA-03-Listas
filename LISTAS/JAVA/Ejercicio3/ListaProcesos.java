package Ejercicio3;

public class ListaProcesos {

    private class Nodo {
        String nombre;
        int tiempoRestante;
        Nodo siguiente;
        Nodo(String nombre, int tiempo) {
            this.nombre = nombre;
            this.tiempoRestante = tiempo;
        }
    }

    private Nodo cabeza;
    private Nodo cola;

    public boolean estaVacia() {
        return cabeza == null;
    }

    // Agrega un proceso al final de la lista circular
    public void agregar(String nombre, int tiempo) {
        Nodo nuevo = new Nodo(nombre, tiempo);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.siguiente = cabeza;
            cola = nuevo;
        }
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("   Lista: (vacia, todos los procesos terminaron)");
            return;
        }
        StringBuilder sb = new StringBuilder("   Lista: ");
        Nodo actual = cabeza;
        do {
            sb.append(actual.nombre).append("(").append(actual.tiempoRestante).append(")");
            actual = actual.siguiente;
            if (actual != cabeza) sb.append(" -> ");
        } while (actual != cabeza);
        System.out.println(sb.toString());
    }

    // Simula Round-Robin con el quantum indicado
    public void ejecutarRoundRobin(int quantum) {
        if (estaVacia()) {
            System.out.println("No hay procesos para ejecutar.");
            return;
        }

        Nodo actual = cabeza;
        Nodo anterior = cola; // el anterior a cabeza, por ser circular, es cola
        int turno = 1;

        while (!estaVacia()) {
            int ejecutado = Math.min(quantum, actual.tiempoRestante);
            actual.tiempoRestante -= ejecutado;

            System.out.println("Turno " + turno + ": ejecuta " + actual.nombre +
                    " (usa " + ejecutado + " u., restante " + actual.tiempoRestante + ")");

            Nodo siguienteNodo = actual.siguiente;

            if (actual.tiempoRestante <= 0) {
                System.out.println("   " + actual.nombre + " termino y sale de la lista.");
                if (actual == cabeza && actual == cola) {
                    cabeza = null; // era el unico proceso
                    cola = null;
                } else {
                    anterior.siguiente = actual.siguiente; // saca el nodo del circulo
                    if (actual == cabeza) cabeza = actual.siguiente;
                    if (actual == cola) cola = anterior;
                }
                actual = siguienteNodo; // anterior no cambia: ahora precede al nuevo actual
            } else {
                anterior = actual;
                actual = actual.siguiente; // pasa al final del ciclo (round-robin)
            }

            mostrar();
            turno++;
        }
        System.out.println("Todos los procesos han finalizado.");
    }
}
