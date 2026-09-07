# ESTRUCTURA--TAREA-03-Listas


## Ejercicio 1 — Lista Simplemente Enlazada Circular

Este ejercicio implementa la estructura base sobre la cual se construyen los cuatro ejercicios siguientes: una lista enlazada donde, en lugar de que el último nodo apunte a `null` como en una lista lineal, apunta de vuelta al primer nodo, formando un ciclo cerrado.

**Variables utilizadas:**
| Variable | Tipo | Descripción |
|---|---|---|
| `cabeza` | `Nodo` | Referencia al primer nodo de la lista. |
| `cola` | `Nodo` | Referencia al último nodo; su campo `siguiente` siempre apunta a `cabeza`. |
| `contador` | `int` | Cantidad de elementos actualmente en la lista. |
| `dato` (dentro de `Nodo`) | `int` | Valor almacenado en cada nodo. |
| `siguiente` (dentro de `Nodo`) | `Nodo` | Referencia al nodo posterior en el círculo. |

**Métodos principales:**
- `insertarAlInicio(dato)`: crea un nuevo nodo y lo convierte en la nueva cabeza, reconectando `cola.siguiente` hacia ella para no romper el círculo.
- `insertarAlFinal(dato)`: coloca el nuevo nodo después de la cola actual y lo convierte en la nueva cola, cerrando el círculo hacia `cabeza`.
- `mostrar()`: recorre la lista con un ciclo `do-while` que se detiene al volver a `cabeza`, no al llegar a `null`.
- `estaVacia()` y `contar()`: consultan el estado de la lista en O(1), sin modificarla.

**¿Cómo diseñamos el TDA?**
Se partió de identificar el *estado mínimo necesario*: para administrar un círculo de nodos alcanza con conocer el primero (`cabeza`) y el último (`cola`), ya que ambos son los únicos puntos donde se insertan elementos nuevos. Se agregó `contador` como dato derivado (no indispensable, pues podría calcularse recorriendo la lista, pero se mantiene como caché para responder `contar()` en O(1) en vez de O(n)). Las operaciones se definieron directamente a partir de los verbos del enunciado (insertar al inicio, insertar al final, mostrar, verificar vacío, contar), y el invariante central que gobierna todo el diseño es uno solo: *el último nodo siempre debe apuntar al primero*. Cada método que modifica la lista se revisó específicamente para asegurar que ese invariante se mantenga después de ejecutarse.

---

## Ejercicio 2 — Inserción y Eliminación Controlada

Extiende la lista del ejercicio 1 agregando operaciones indexadas por posición y por valor, además de exigir mostrar el estado antes y después de cada cambio.

**Variables utilizadas:**
| Variable | Tipo | Descripción |
|---|---|---|
| `cabeza`, `cola` | `Nodo` | Extremos del círculo, igual que en el ejercicio 1. |
| `contador` | `int` | Cantidad de nodos; se usa además para **validar rangos de posición** (0 ≤ posición ≤ contador). |
| `posicion` | `int` (parámetro) | Índice donde se inserta o elimina un elemento. |
| `valor` | `int` (parámetro) | Dato buscado en `eliminarPorValor()`. |
| `anterior` | `Nodo` (variable local) | Referencia al nodo previo al punto de inserción/eliminación; necesaria porque la lista es simplemente enlazada (no se puede retroceder desde el nodo actual). |
| `vueltas` | `int` (variable local) | Contador de recorrido en `eliminarPorValor()`, usado en vez de comparar contra `cabeza`, para poder detectar coincidencias justo en la cabeza. |

**Métodos principales:**
- `insertarEnPosicion(dato, pos)`: distingue tres casos (inicio, final, intermedio) y reenlaza el punto correspondiente.
- `eliminarPorPosicion(pos)`: separa el caso de lista vacía, el caso de eliminar la cabeza, y el caso general (nodo intermedio o cola).
- `eliminarPorValor(valor)`: recorre comparando cada dato hasta encontrar la primera coincidencia.
- `mostrar()`: se invoca explícitamente antes y después de cada operación.

**¿Cómo diseñamos la estructura?**
Aquí el diseño partió de una pregunta clave: *¿qué información adicional necesito para insertar o eliminar en cualquier punto, no solo en los extremos?* La respuesta fue mantener una referencia `anterior` durante el recorrido, ya que en una lista simplemente enlazada no existe forma de "mirar hacia atrás" desde un nodo. Se identificaron explícitamente los tres invariantes de posición: nunca se acepta una posición fuera de `[0, contador]` al insertar, ni fuera de `[0, contador-1]` al eliminar; y tras cualquier eliminación que vacíe la lista, `cabeza` y `cola` deben quedar en `null` simultáneamente. Diseñar pensando primero en los **casos borde** (vacía, un nodo, varios nodos) antes de escribir el caso general fue la estrategia que evitó dejar huecos o referencias colgantes.

---

## Ejercicio 3 — Simulación de Turnos Round-Robin

Aplica la lista circular a un problema de sistemas operativos: repartir tiempo de CPU entre procesos de forma rotativa.

**Variables utilizadas:**
| Variable | Tipo | Descripción |
|---|---|---|
| `cabeza`, `cola` | `Nodo` | Extremos del círculo de procesos. |
| `nombre` (en `Nodo`) | `String` | Identificador del proceso. |
| `tiempoRestante` (en `Nodo`) | `int` | Unidades de CPU que aún necesita el proceso; disminuye en cada turno. |
| `quantum` | `int` (parámetro) | Porción fija de tiempo asignada por turno (2 unidades). |
| `ejecutado` | `int` (variable local) | `Math.min(quantum, tiempoRestante)`: tiempo real consumido en el turno actual. |
| `actual` | `Nodo` (variable local) | Proceso al que le toca turno en el ciclo. |
| `anterior` | `Nodo` (variable local) | Nodo previo a `actual`, necesario para desenlazarlo si el proceso termina. |

**Métodos principales:**
- `agregar(nombre, tiempo)`: inserta un nuevo proceso al final del círculo.
- `ejecutarRoundRobin(quantum)`: calcula el tiempo consumido, lo resta, y decide si el proceso se elimina (terminó) o simplemente avanza al siguiente nodo (no terminó), aprovechando que el avance natural en un círculo ya equivale a "mandarlo al final de la cola".

**¿Cómo diseñamos el TDA?**
El diseño se centró en modelar el **estado de cada proceso** (nombre + tiempo restante) como parte del propio nodo, en vez de usar una estructura auxiliar separada, porque el ciclo de vida de un proceso está intrínsecamente ligado a su posición en el círculo. La operación central (`ejecutarRoundRobin`) se diseñó identificando primero las dos únicas transiciones posibles en cada turno —el proceso termina o no termina— y traduciendo cada una a una operación ya conocida de listas circulares: eliminar un nodo (si termina) o simplemente avanzar el puntero (si no termina). Este último punto fue la decisión de diseño más importante: **no fue necesario programar explícitamente "mover al final de la cola"**, porque la propia circularidad de la lista produce ese efecto de forma gratuita al avanzar el puntero.

---

## Ejercicio 4 — Problema de Josephus

Implementa el clásico problema: n personas en círculo, se elimina cada k-ésima hasta que queda una sola.

**Variables utilizadas:**
| Variable | Tipo | Descripción |
|---|---|---|
| `persona` (en `Nodo`) | `int` | Número identificador de cada persona en el círculo (1 a n). |
| `n` | `int` (parámetro) | Cantidad total de personas a ubicar en el círculo. |
| `k` | `int` (parámetro) | Cada cuántas personas se cuenta antes de eliminar. |
| `actual` | `Nodo` (variable local) | Nodo en el que se encuentra el conteo en cada vuelta. |
| `previo` | `Nodo` (variable local) | Nodo anterior a `actual`; imprescindible para poder eliminar (una lista simplemente enlazada no permite "saltar hacia atrás"). |

**Métodos principales:**
- `resolver(n, k)`: arma el círculo de n nodos, y luego usa `previo`/`actual` avanzando k-1 pasos por vuelta; al llegar al nodo k-ésimo, lo elimina reconectando `previo.siguiente` y reinicia el conteo desde ahí. Se repite mientras `actual.siguiente != actual`.

Se probó con n=5, k=2 (orden: 2, 4, 1, 5; sobreviviente: 3) y n=7, k=3 (orden: 3, 6, 2, 7, 5, 1; sobreviviente: 4).

**¿Cómo diseñamos la estructura?**
A diferencia de los ejercicios anteriores, aquí no hace falta mantener `cabeza` ni `cola` como atributos persistentes del objeto, porque el círculo se construye y se consume dentro de una sola llamada a `resolver()`. La decisión de diseño clave fue usar **dos punteros que avanzan sincronizados** (`previo` y `actual`) en lugar de uno solo, precisamente porque eliminar un nodo en una lista simplemente enlazada exige conocer al nodo anterior. La condición de parada (`actual.siguiente != actual`) se diseñó pensando en el invariante natural del problema: cuando solo queda una persona, esta se enlaza consigo misma, lo cual es indistinguible de la condición de "único nodo" ya usada en los ejercicios previos.

---

## Ejercicio 5 — Caso Aplicado: Sistema de Reproducción Circular

Traslada la lista circular a un contexto de software real: una playlist musical.

**Variables utilizadas:**
| Variable | Tipo | Descripción |
|---|---|---|
| `cabeza`, `cola` | `Nodo` | Extremos del círculo de canciones. |
| `actual` | `Nodo` | **Variable exclusiva de este ejercicio**: indica qué canción se está reproduciendo en cada momento (no es un extremo, sino un puntero de "posición de lectura"). |
| `cancion` (en `Nodo`) | `String` | Nombre de la canción almacenada en cada nodo. |
| `anterior` | `Nodo` (variable local, en `eliminarPorNombre`) | Nodo previo a la canción buscada, necesario para desenlazarla. |

**Métodos principales:**
- `agregarAlInicio(cancion)` / `agregarAlFinal(cancion)`: insertan manteniendo el círculo cerrado.
- `mostrarPlaylist()`: imprime todas las canciones, marcando entre corchetes la actual.
- `reproducirSiguiente()`: avanza `actual = actual.siguiente`; no contiene ninguna condición especial para "volver al inicio" — ese comportamiento surge solo de la circularidad de la estructura.
- `eliminarPorNombre(cancion)`: si la canción eliminada era la que sonaba, reasigna `actual` a la siguiente antes de desenlazarla, para nunca dejar una referencia inválida.

**¿Cómo diseñamos el TDA?**
Este ejercicio requirió agregar una variable de estado que no existía en los anteriores: `actual`, que representa la "posición de reproducción" y es conceptualmente distinta de `cabeza`/`cola` (que representan los extremos físicos de la lista, no un punto de lectura). El diseño identificó un riesgo particular: ¿qué pasa si se elimina justo la canción que está sonando? Por eso `eliminarPorNombre()` se diseñó verificando explícitamente si el nodo a eliminar coincide con `actual`, reasignándolo *antes* de desenlazar el nodo, evitando que `actual` quede apuntando a un nodo que ya no pertenece a la lista. La ventaja de diseño más importante que se buscó demostrar en este ejercicio es que la regla de negocio "al llegar al final, vuelve al inicio" no se programó como lógica adicional, sino que quedó incorporada de forma natural en la estructura de datos misma.
