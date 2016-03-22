package no.mesan.battleship.domain

@native data class Coordinate(val x: Int, val y: Int) {
    init {
        require(x >= 0) { "X was negative ($x)" }
        require(y >= 0) { "Y was negative ($y)" }
    }
}