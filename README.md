# ESTRUCTURA--TAREA-03-Listas
1. Lista simplemente enlazada circular

Este ejercicio implementa una lista enlazada circular, donde los elementos están conectados mediante nodos y el último nodo apunta nuevamente al primero. El programa permite insertar elementos al inicio y al final, mostrar todos los elementos, comprobar si la lista está vacía y contar cuántos elementos contiene.

Métodos principales:

insertarInicio(): agrega un elemento al comienzo.
insertarFinal(): agrega un elemento al final.
mostrar(): recorre y muestra todos los elementos.
estaVacia(): verifica si existen elementos.
contar(): devuelve el número de nodos.

La característica circular permite recorrer nuevamente desde el primer elemento después de llegar al último.

2. Inserción y eliminación controlada

Este ejercicio trabaja con una lista circular que permite modificar su contenido de manera controlada. El programa puede insertar un elemento en una posición determinada y eliminar elementos tanto por su posición como por su valor. Además, muestra la lista antes y después de cada operación para observar los cambios.

Métodos principales:

insertarPosicion(): inserta un elemento en una posición específica.
eliminarPosicion(): elimina el nodo ubicado en una posición.
eliminarValor(): busca y elimina un elemento por su valor.
mostrar(): imprime los elementos de la lista.
estaVacia(): comprueba si la lista no contiene nodos.

También considera diferentes situaciones: lista vacía, lista con un solo nodo y lista con varios nodos, ya que las operaciones pueden comportarse de manera diferente en cada caso.

3. Simulación de turnos Round-Robin

Este ejercicio utiliza una lista circular para simular la ejecución de procesos mediante el algoritmo Round-Robin. Cada proceso posee un nombre y un tiempo restante de ejecución. El programa utiliza un quantum de 2 unidades, por lo que cada proceso recibe como máximo dos unidades de tiempo antes de pasar al siguiente.

Métodos principales:

agregarProceso(): incorpora un nuevo proceso a la lista.
ejecutarTurno(): ejecuta el proceso actual durante el quantum establecido.
eliminarProceso(): elimina un proceso cuando termina.
mostrarProcesos(): muestra el estado actual de la lista.
estaVacia(): verifica si quedan procesos por ejecutar.

La lista circular es útil porque permite pasar automáticamente de un proceso al siguiente y continuar nuevamente desde el primero.

4. Resolución del problema de Josephus

Este ejercicio implementa el problema de Josephus utilizando una lista circular. Se colocan varias personas formando un círculo y se elimina cada k-ésima persona hasta que solamente queda una. El programa permite realizar diferentes pruebas y mostrar tanto el orden en que las personas son eliminadas como el superviviente final.

Métodos principales:

crearPersonas(): crea los nodos que representan a las personas.
eliminarCadaK(): realiza las eliminaciones siguiendo el valor de k.
eliminarNodo(): elimina una persona de la lista.
mostrar(): muestra las personas que permanecen.
obtenerSuperviviente(): determina la persona que queda al final.

La lista circular es adecuada porque después de la última persona se continúa automáticamente con la primera, reproduciendo exactamente el comportamiento de las personas ubicadas en un círculo.

5. Sistema de reproducción circular

Este ejercicio representa una playlist musical mediante una lista circular. El programa permite agregar canciones al inicio o al final, mostrar todas las canciones, reproducir la siguiente canción y eliminar canciones mediante su nombre.

Métodos principales:

agregarInicio(): agrega una canción al comienzo.
agregarFinal(): agrega una canción al final.
mostrarPlaylist(): muestra todas las canciones.
reproducirSiguiente(): avanza hacia la siguiente canción.
eliminarCancion(): busca y elimina una canción por su nombre.
estaVacia(): comprueba si la playlist tiene canciones.

La principal ventaja de utilizar una lista circular es que cuando se llega a la última canción, el recorrido vuelve automáticamente a la primera, permitiendo una reproducción continua sin necesidad de reiniciar manualmente la playlist.
