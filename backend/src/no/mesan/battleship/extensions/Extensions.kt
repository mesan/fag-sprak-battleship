package no.mesan.battleship.extensions

/**
 * Denne filen inneholder diverse generelle extension functions.
 * Dersom man vil lage en extension function som kun gir mening for en gitt
 * fil eller klasse, legg heller funksjonen i den samme filen og gjør den private.
 */


// Fant ikke minus på kotlin.Map. Hvorfor?
operator fun <K, V> Map<K, V>.minus(key: K): Map<K, V> = filterNot { it.key == key }


// Higher order functions on 2D lists.
fun <T, R> List<List<T>>.map2d(mapper: (T) -> R): List<List<R>> = map { it.map { mapper(it) } }

fun <T> List<List<T>>.filter2d(predicate: (T) -> Boolean): List<List<T>> = map { it.filter { predicate(it) } }

fun <T> List<List<T>>.all2d(predicate: (T) -> Boolean): Boolean = all { it.all { predicate(it) } }

fun <T> List<List<T>>.any2d(predicate: (T) -> Boolean): Boolean = any { it.any { predicate(it) } }


// 2D list creation functions
fun <T> listOfLists(xSize: Int, ySize: Int, initializer: (Int, Int) -> T): List<List<T>> {
    val list = mutableListOf<List<T>>()

    for (y in 0 until ySize) {
        val inner = mutableListOf<T>()
        for (x in 0 until xSize) {
            inner.add(initializer(x, y))
        }
        list.add(inner)
    }

    return list
}

fun <T> listOfLists(size: Int, initializer: (Int, Int) -> T): List<List<T>> = listOfLists(size, size, initializer)