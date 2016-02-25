package no.mesan.battleship.extensions

/**
 * Denne filen inneholder diverse generelle extension functions.
 * Dersom man vil lage en extension function som kun gir mening for en gitt
 * fil eller klasse, legg heller funksjonen i den samme filen og gjør den private.
 */


// Fant ikke minus på kotlin.Map. Hvorfor?
operator fun <K, V> Map<K, V>.minus(key: K): Map<K, V> = filterNot { it.key == key }


// 2D array higher order functions.
inline fun <T, reified R> Array<Array<T>>.map2d(mapper: (T) -> R): Array<Array<R>> {
    return map { column ->
        column.map { value ->
            mapper(value)
        }.toTypedArray()
    }.toTypedArray()
}

inline fun <reified T> Array<Array<T>>.filter2d(predicate: (T) -> Boolean): Array<Array<T>> {
    return map { column ->
        column.filter { value ->
            predicate(value)
        }.toTypedArray()
    }.toTypedArray()
}

inline fun <T> Array<Array<T>>.all2d(predicate: (T) -> Boolean): Boolean {
    return all { column ->
        column.all { value ->
            predicate(value)
        }
    }
}
}