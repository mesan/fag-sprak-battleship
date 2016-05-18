package no.mesan.battleship.domain

data class Ship(val start: Coordinate, val end: Coordinate) {
    init {
        require(start.x == end.x || start.y == end.y) {
            "The ship must be placed either horizontally or vertically"
        }
    }

    fun contains(coordinate: Coordinate): Boolean {
        return coordinate.y >= Math.min(start.y, end.y)
                && coordinate.y <= Math.max(start.y, end.y)
                && coordinate.x >= Math.min(start.x, end.x)
                && coordinate.x <= Math.max(start.x, end.x);
    }

    fun sectionTypeAt(coordinate:Coordinate): String? {
        if (!contains(coordinate)) return null;

        if (coordinate == start) {
            if (start.x < end.x) return "left"
            if (start.x > end.x) return "right"
            if (start.y < end.y) return "top"
            if (start.y > end.y) return "bottom"
        }
        if (coordinate == end) {
            if (start.x < end.x) return "right"
            if (start.x > end.x) return "left"
            if (start.y < end.y) return "bottom"
            if (start.y > end.y) return "top"
        }

        return "middle"
    }
}