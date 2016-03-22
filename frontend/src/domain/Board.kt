package no.mesan.battleship.domain

@native data class Board(val board: List<List<Cell>>) {
    init {
        val size = if (board.size > 0) board[0].size else 0
        for (column in board) {
            if (column.size != size) {
                throw IllegalArgumentException("Board is not square")
            }
        }
    }
}