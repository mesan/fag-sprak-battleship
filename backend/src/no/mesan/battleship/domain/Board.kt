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

        // (0, (1,2)), (0, (1,3)),(0, (1,4))
        val indexedShipsWithCoords: Map<Pair<Int,Int>, Int> =
                ships.withIndex()
                        .flatMap { iv -> iv.value.getShipCoords().map { coord -> Pair(coord, iv.index) } }
                        .toMap()

        val cords = (0 until board.size).map { x -> (0 until board.size).map { y -> Pair(x,y)}} // 2d liste
        val newCells = cords.map { pair -> pair .map { pair -> Cell(pair.first * pair.second, indexedShipsWithCoords.get(pair), false)} }

        return Board(newCells)
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