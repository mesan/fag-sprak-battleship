package no.mesan.battleship.domain

@native data class Ship(val start: Coordinate, val end: Coordinate) {
    init {
        require(start.x != end.x && start.y != end.y) {
            "The ship must be placed either horizontally or vertically"
        }
    }
}