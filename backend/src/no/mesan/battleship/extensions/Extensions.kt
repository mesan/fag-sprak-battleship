package no.mesan.battleship.extensions

/**
 * Denne filen inneholder diverse generelle extension functions.
 * Dersom man vil lage en extension function som kun gir mening for en gitt
 * fil eller klasse, legg heller funksjonen i den samme filen og gjør den private.
 */


// Fant ikke minus på kotlin.Map. Hvorfor?
operator fun <K, V> Map<K, V>.minus(key: K): Map<K, V> = filterNot { it.key == key }