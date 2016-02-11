package no.mesan.battleship.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Cell(val isHit: Boolean = false, val isOccupied: Boolean = false) {

    fun hit() = if (isHit) this else Cell(true, isOccupied)

}

data class Board(val board: Array<Array<Cell>>) {
    init {
        val size = if (board.size > 0) board[0].size else 0
        for (arr in board) {
            if (arr.size != size) {
                throw IllegalArgumentException("Board is not rectangular.")
            }
        }
    }

    companion object {
        fun empty(size: Int) = Board(Array(size, { Array(size, { Cell() }) }))
    }

}

data class Coordinate(@JsonProperty(value = "first") val first: Int, @JsonProperty(value = "second") val second: Int)

data class Ship(@JsonProperty(value = "start") val start: Coordinate, @JsonProperty(value = "end") val end: Coordinate) {

    init {
        if ((start.first != end.first && start.second != end.second)) {
            throw IllegalArgumentException("Ship must be placed either horizontally or vertically.")
        }
    }

    fun length() = Math.abs(end.first - start.first) + Math.abs(end.second - start.second) + 1

}
