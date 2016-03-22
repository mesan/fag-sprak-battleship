package no.mesan.battleship.domain

import no.mesan.battleship.extensions.all2d
import no.mesan.battleship.extensions.filter2d
import no.mesan.battleship.extensions.listOfLists
import no.mesan.battleship.extensions.map2d

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
        fun empty(size: Int) = Board(listOfLists(size, { Cell(it) }))
        fun empty(xSize: Int, ySize: Int) = Board(listOfLists(xSize, ySize, { Cell(it) }))
    }

    fun withShips(ships: List<Ship>): Board {
        require(board.all2d { !it.isOccupied })
        throw NotImplementedError()
    }

    fun hit(coordinate: Coordinate): Board {
        val cell = this[coordinate] ?: throw IllegalArgumentException("Coordinate out of bounds.")

        if (cell.isHit) {
            throw IllegalArgumentException("Cell is already hit");
        }

        return Board(board.map2d {
            if (it.id == cell.id) {
                cell.hit()
            } else {
                it
            }
        })
    }

    fun isCompleted(): Boolean {
        return board.filter2d { it.isOccupied }
                .all2d { it.isHit }
    }

    private operator fun get(coordinate: Coordinate): Cell? {
        if (coordinate.x > board.size || coordinate.y > board[0].size) {
            return null
        } else {
            return board[coordinate.x][coordinate.y]
        }
    }
}