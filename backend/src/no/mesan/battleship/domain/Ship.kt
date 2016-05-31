package no.mesan.battleship.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Ship(@JsonProperty("start") val start: Coordinate, @JsonProperty("end") val end: Coordinate) {

    init {
        require(start.x == end.x || start.y == end.y) {
            "The ship must be placed either horizontally or vertically."
        }
    }

    fun length() = Math.abs(end.x - start.x) + Math.abs(end.y - start.y) + 1

    fun getShipCoords(): List<Coordinate> =
            (start.x..end.x).flatMap() { x -> (start.y..end.y).map { y -> Coordinate(x,y) }}

}