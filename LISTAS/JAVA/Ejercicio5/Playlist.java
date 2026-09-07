package Ejercicio5;

public class Playlist {

    private class Nodo {
        String cancion;
        Nodo siguiente;
        Nodo(String cancion) { this.cancion = cancion; }
    }

    private Nodo cabeza;
    private Nodo cola;
    private Nodo actual; // cancion que se esta reproduciendo

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void agregarAlInicio(String cancion) {
        Nodo nuevo = new Nodo(cancion);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = nuevo;
            actual = nuevo; // primera cancion cargada
        } else {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
            cola.siguiente = cabeza; // re-cerrar el circulo
        }
    }

    public void agregarAlFinal(String cancion) {
        Nodo nuevo = new Nodo(cancion);
        if (estaVacia()) {
            cabeza = nuevo;
            cola = nuevo;
            nuevo.siguiente = nuevo;
            actual = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.siguiente = cabeza;
            cola = nuevo;
        }
    }

    public void mostrarPlaylist() {
        if (estaVacia()) {
            System.out.println("La playlist esta vacia.");
            return;
        }
        StringBuilder sb = new StringBuilder("Playlist: ");
        Nodo n = cabeza;
        do {
            sb.append(n == actual ? "[" + n.cancion + "]" : n.cancion); // marca la actual
            n = n.siguiente;
            if (n != cabeza) sb.append(" -> ");
        } while (n != cabeza);
        System.out.println(sb.toString());
    }

    // Avanza a la siguiente cancion; al llegar al final vuelve sola al inicio
    public void reproducirSiguiente() {
        if (estaVacia()) {
            System.out.println("No hay canciones en la playlist.");
            return;
        }
        actual = actual.siguiente; // el circulo hace que vuelva a cabeza automaticamente
        System.out.println("Reproduciendo: " + actual.cancion);
    }

    public void eliminarPorNombre(String cancion) {
        if (estaVacia()) {
            System.out.println("La playlist esta vacia.");
            return;
        }

        if (cabeza == cola) { // un solo nodo
            if (cabeza.cancion.equals(cancion)) {
                cabeza = null;
                cola = null;
                actual = null;
            } else {
                System.out.println("Cancion no encontrada.");
            }
            return;
        }

        Nodo prev = cola;
        Nodo n = cabeza;
        do {
            if (n.cancion.equals(cancion)) {
                prev.siguiente = n.siguiente;
                if (n == cabeza) cabeza = n.siguiente;
                if (n == cola) cola = prev;
                if (n == actual) actual = n.siguiente; // si sonaba esta, pasa a la siguiente
                System.out.println("Cancion eliminada: " + cancion);
                return;
            }
            prev = n;
            n = n.siguiente;
        } while (n != cabeza);

        System.out.println("Cancion no encontrada.");
    }
}
