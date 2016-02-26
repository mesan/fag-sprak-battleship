package no.mesan.battleship.extensions

/**
 * Denne filen inneholder diverse generelle extension functions.
 * Dersom man vil lage en extension function som kun gir mening for en gitt
 * fil eller klasse, legg heller funksjonen i den samme filen og gjør den private.
 */


// Fant ikke minus på kotlin.Map. Hvorfor?
operator fun <K, V> Map<K, V>.minus(key: K): Map<K, V> = filterNot { it.key == key }


// 2D array higher order functions.
fun <T, R> List<List<T>>.map2d(mapper: (T) -> R): List<List<R>> = map { it.map { mapper(it) } }

fun <T> List<List<T>>.filter2d(predicate: (T) -> Boolean): List<List<T>> = map { it.filter { predicate(it) } }

fun <T> List<List<T>>.all2d(predicate: (T) -> Boolean): Boolean = all { it.all { predicate(it) } }

fun <T> List<List<T>>.any2d(predicate: (T) -> Boolean): Boolean = any { it.any { predicate(it) } }


// List creation functions
fun <T> listOfLists(xSize: Int, ySize: Int, initializer: (Int) -> T): List<List<T>> {
    val list = mutableListOf<List<T>>()

    for (i in 0 until xSize) {
        val inner = mutableListOf<T>()
        for (j in 0 until ySize) {
            inner.add(initializer(i * ySize + j))
        }
        list.add(inner)
    }

    return list
}

fun <T> listOfLists(size: Int, initializer: (Int) -> T): List<List<T>> = listOfLists(size, size, initializer)