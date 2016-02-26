package no.mesan.battleship.domain

import com.fasterxml.jackson.annotation.JsonProperty
import no.mesan.battleship.extensions.all2d
import no.mesan.battleship.extensions.filter2d
import no.mesan.battleship.extensions.listOfLists

data class Cell(val isHit: Boolean = false, val isOccupied: Boolean = false) {

    fun hit() = if (isHit) this else Cell(true, isOccupied)

}

data class Board(val board: List<List<Cell>>) {
    init {
        val size = if (board.size > 0) board[0].size else 0
        for (column in board) {
            if (column.size != size) {
                throw IllegalArgumentException("Board is not a square.")
            }
        }
    }

    companion object {
        fun empty(size: Int) = Board(listOfLists(size, { Cell() }))
        fun empty(xSize: Int, ySize: Int) = Board(listOfLists(xSize, ySize, { Cell() }))
    }

    fun withShips(ships: List<Ship>): Board {
        require(board.all2d { !it.isOccupied })
        throw NotImplementedError()
    }

    fun hit(square: Coordinate): Board {
        throw NotImplementedError()
    }

    fun isCompleted(): Boolean {
        return board.filter2d { it.isOccupied }
                .all2d { it.isHit }
    }

}

data class Coordinate(@JsonProperty("x") val x: Int, @JsonProperty("y") val y: Int)

data class Ship(@JsonProperty("start") val start: Coordinate, @JsonProperty("end") val end: Coordinate) {

    init {
        require(start.x != end.x && start.y != end.y) {
            "The ship must be placed either horizontally or vertically."
        }
    }

    fun length() = Math.abs(end.x - start.x) + Math.abs(end.y - start.y) + 1

}
