package Ejercicio2;

public class ListaCircular {

    private class Nodo {
        int dato;
        Nodo siguiente;
        Nodo(int dato) { this.dato = dato; }
    }

    private Nodo cabeza;
    private Nodo cola;
    private int contador; // numero de elementos actuales

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int contar() {
        return contador;
    }

    // Inserta en la posicion indicada (0 = inicio, contador = final)
    public void insertarEnPosicion(int dato, int posicion) {
        if (posicion < 0 || posicion > contador) {
            System.out.println("Posicion invalida.");
            return;
        }
        Nodo nuevo = new Nodo(dato);

        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = nuevo; // unico nodo: se apunta a si mismo
        } else if (posicion == 0) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            cola.siguiente = cabeza; // re-cerrar el circulo
        } else if (posicion == contador) {
            cola.siguiente = nuevo;
            nuevo.siguiente = cabeza;
            cola = nuevo;
        } else {
            Nodo anterior = cabeza;
            for (int i = 0; i < posicion - 1; i++) {
                anterior = anterior.siguiente;
            }
            nuevo.siguiente = anterior.siguiente;
            anterior.siguiente = nuevo;
        }
        contador++;
    }

    // Elimina el nodo ubicado en la posicion indicada
    public void eliminarPorPosicion(int posicion) {
        if (estaVacia() || posicion < 0 || posicion >= contador) {
            System.out.println("Posicion invalida o lista vacia.");
            return;
        }

        if (contador == 1) {
            cabeza = null;
            cola = null;
        } else if (posicion == 0) {
            cabeza = cabeza.siguiente;
            cola.siguiente = cabeza; // re-cerrar el circulo
        } else {
            Nodo anterior = cabeza;
            for (int i = 0; i < posicion - 1; i++) {
                anterior = anterior.siguiente;
            }
            Nodo aEliminar = anterior.siguiente;
            anterior.siguiente = aEliminar.siguiente;
            if (aEliminar == cola) {
                cola = anterior;
            }
        }
        contador--;
    }

    // Elimina la primera ocurrencia del valor indicado
    public void eliminarPorValor(int valor) {
        if (estaVacia()) {
            System.out.println("Lista vacia.");
            return;
        }

        if (contador == 1) {
            if (cabeza.dato == valor) {
                cabeza = null;
                cola = null;
                contador--;
            } else {
                System.out.println("Valor no encontrado.");
            }
            return;
        }

        Nodo actual = cabeza;
        Nodo anterior = cola; // el anterior a cabeza es cola (circular)
        int vueltas = 0;

        while (vueltas < contador) {
            if (actual.dato == valor) {
                if (actual == cabeza) {
                    cabeza = cabeza.siguiente;
                    cola.siguiente = cabeza;
                } else {
                    anterior.siguiente = actual.siguiente;
                    if (actual == cola) {
                        cola = anterior;
                    }
                }
                contador--;
                return;
            }
            anterior = actual;
            actual = actual.siguiente;
            vueltas++;
        }
        System.out.println("Valor no encontrado.");
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("Lista: (vacia)");
            return;
        }
        StringBuilder sb = new StringBuilder("Lista: ");
        Nodo actual = cabeza;
        do {
            sb.append(actual.dato);
            actual = actual.siguiente;
            if (actual != cabeza) sb.append(" -> ");
        } while (actual != cabeza);
        sb.append(" -> (vuelve a ").append(cabeza.dato).append(")");
        System.out.println(sb.toString());
    }
}
