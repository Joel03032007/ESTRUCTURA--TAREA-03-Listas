# ESTRUCTURA--TAREA-03-Listas
# Descripción de los 5 Ejercicios

## Ejercicio 1 — Lista Simplemente Enlazada Circular

Este ejercicio implementa la estructura base sobre la cual se construyen los cuatro ejercicios siguientes: una lista enlazada donde, en lugar de que el último nodo apunte a `null` como en una lista lineal, apunta de vuelta al primer nodo, formando un ciclo cerrado. La clase `ListaCircular` administra internamente objetos `Nodo` (cada uno con un dato entero y una referencia `siguiente`), y mantiene tres variables de control: `cabeza` (primer nodo), `cola` (último nodo) y `contador` (cantidad de elementos).

**Métodos principales:**
- `insertarAlInicio(dato)`: crea un nuevo nodo y lo convierte en la nueva cabeza. Si la lista está vacía, el nodo se enlaza consigo mismo; si no, se reconecta `cola.siguiente` hacia la nueva cabeza para no romper el círculo.
- `insertarAlFinal(dato)`: similar, pero el nuevo nodo se coloca después de la cola actual, y luego se convierte en la nueva cola, cerrando el círculo hacia `cabeza`.
- `mostrar()`: recorre la lista con un ciclo `do-while` que se detiene al volver a `cabeza` (no al llegar a `null`, porque en esta estructura nunca existe un `null` entre los nodos).
- `estaVacia()` y `contar()`: consultan el estado de la lista sin modificarla, en tiempo O(1).

El valor pedagógico central de este ejercicio es entender por qué la conexión `cola.siguiente = cabeza` debe reforzarse en cada inserción: sin ella, la lista dejaría de ser circular.

---

## Ejercicio 2 — Inserción y Eliminación Controlada

Este ejercicio extiende la lista del ejercicio 1 agregando operaciones indexadas por posición y por valor, además de exigir mostrar el estado de la lista antes y después de cada cambio. La dificultad aumenta porque ahora hay que mantener consistentes `cabeza`, `cola` y el enlace circular sin importar en qué punto de la lista ocurra la operación.

**Métodos principales:**
- `insertarEnPosicion(dato, pos)`: distingue tres casos — posición 0 (equivale a insertar al inicio), posición igual a `contador` (equivale a insertar al final), y cualquier posición intermedia (recorre hasta el nodo anterior y reenlaza ahí).
- `eliminarPorPosicion(pos)`: localiza el nodo en esa posición y lo desenlaza. Maneja de forma separada el caso de que la lista quede vacía, el caso de eliminar la cabeza, y el caso general (nodo intermedio o cola).
- `eliminarPorValor(valor)`: recorre la lista comparando cada dato hasta encontrar la primera coincidencia, usando un contador de vueltas (no una comparación contra `cabeza`) para poder detectar correctamente si el valor buscado está justo en la cabeza.
- `mostrar()`: se invoca explícitamente antes y después de cada operación para evidenciar el cambio.

Este ejercicio pone a prueba el manejo de los tres escenarios críticos de cualquier lista enlazada: lista vacía, lista de un solo nodo y lista con varios nodos, cada uno con su propia lógica de ajuste de punteros.

---

## Ejercicio 3 — Simulación de Turnos Round-Robin

Aquí la lista circular se aplica a un problema de sistemas operativos: repartir tiempo de CPU entre varios procesos de forma rotativa. Cada proceso se modela como un nodo con `nombre` y `tiempoRestante`. La clase `ListaProcesos` administra el círculo y ejecuta la simulación completa con un quantum fijo de 2 unidades de tiempo por turno.

**Métodos principales:**
- `agregar(nombre, tiempo)`: inserta un nuevo proceso al final del círculo de procesos.
- `ejecutarRoundRobin(quantum)`: es el método central. En cada turno, calcula cuánto tiempo real se consume (`Math.min(quantum, tiempoRestante)`), lo resta, y decide el destino del proceso: si terminó (`tiempoRestante <= 0`), se elimina del círculo reconectando el nodo anterior con el siguiente; si no terminó, simplemente se avanza el puntero `actual` al siguiente nodo — y como la lista es circular, ese simple avance ya logra el efecto de "mandarlo al final de la cola" sin mover nada manualmente.

Este ejercicio demuestra que la circularidad no solo sirve para "recorrer sin fin", sino también para simular colas rotativas de atención, donde cada elemento recibe turnos sucesivos hasta agotar su tarea.

---

## Ejercicio 4 — Problema de Josephus

Implementa el clásico problema de teoría de la computación: n personas ubicadas en círculo, de las cuales se elimina cada k-ésima hasta que queda una sola (el sobreviviente). La clase `Josephus` arma un círculo de nodos numerados de 1 a n y ejecuta el algoritmo de eliminación.

**Métodos principales:**
- `resolver(n, k)`: primero construye el círculo enlazando los n nodos y cerrando el último con el primero. Luego usa dos referencias, `previo` y `actual`, que avanzan juntas k-1 pasos en cada vuelta; al llegar al nodo k-ésimo, se elimina reconectando `previo.siguiente` directamente al nodo que le seguía, y el conteo de la siguiente vuelta se reinicia justo desde ahí. El ciclo se repite mientras `actual.siguiente != actual` (es decir, mientras quede más de una persona).

Se probó con los casos n=5, k=2 (orden de eliminación: 2, 4, 1, 5; sobreviviente: 3) y n=7, k=3 (orden: 3, 6, 2, 7, 5, 1; sobreviviente: 4), ambos coincidentes con los resultados conocidos del problema. Este ejercicio ilustra por qué la lista circular es la estructura idónea aquí: el conteo da vueltas indefinidamente sobre un grupo que se reduce, sin necesitar ninguna condición especial para "reiniciar" el recorrido.

---

## Ejercicio 5 — Caso Aplicado: Sistema de Reproducción Circular

Este último ejercicio traslada la lista circular a un contexto de software real: una playlist musical. La clase `Playlist` agrega una tercera referencia además de `cabeza` y `cola`: `actual`, que indica qué canción se está reproduciendo en cada momento.

**Métodos principales:**
- `agregarAlInicio(cancion)` / `agregarAlFinal(cancion)`: insertan canciones manteniendo el círculo cerrado, igual que en el ejercicio 1.
- `mostrarPlaylist()`: imprime todas las canciones, marcando entre corchetes la que está sonando actualmente.
- `reproducirSiguiente()`: avanza `actual` al siguiente nodo. Es el método clave del ejercicio: no contiene ninguna condición especial para "volver al inicio" al llegar a la última canción, porque el círculo ya conecta la cola con la cabeza — el mismo `actual = actual.siguiente` que avanza normalmente es el que produce el reinicio automático.
- `eliminarPorNombre(cancion)`: busca y desenlaza la canción indicada; si resulta que era la canción que estaba sonando, reasigna `actual` a la siguiente antes de eliminarla, para nunca dejar una referencia inválida.

Este ejercicio cierra la serie demostrando que la ventaja de la lista circular frente a una lineal —evitar lógica adicional para el reinicio del recorrido— tiene una aplicación directa y tangible en productos de software cotidianos, como cualquier reproductor de música con la opción "repetir lista" activada.
