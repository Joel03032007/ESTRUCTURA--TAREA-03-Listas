package Ejercicio1;

public class ListaCircular {

    private class Nodo {
        int dato;
        Nodo siguiente;

        Nodo(int dato) {
            this.dato = dato;
        }
    }

    private Nodo cabeza;   // primer nodo
    private Nodo cola;     // ultimo nodo (permite insertar al final en O(1))
    private int contador;  // cantidad de elementos

    public void insertarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = nuevo; // unico nodo: se apunta a si mismo
        } else {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            cola.siguiente = cabeza; // re-enlazar el circulo
        }
        contador++;
    }

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.siguiente = cabeza; // cierra el circulo hacia la cabeza
            cola = nuevo;
        }
        contador++;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La lista esta vacia.");
            return;
        }
        Nodo actual = cabeza;
        StringBuilder sb = new StringBuilder();
        do {
            sb.append(actual.dato);
            actual = actual.siguiente;
            if (actual != cabeza) sb.append(" -> ");
        } while (actual != cabeza); // se detiene al volver al inicio, no en null
        sb.append(" -> (vuelve a ").append(cabeza.dato).append(")");
        System.out.println(sb.toString());
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int contar() {
        return contador;
    }
}
