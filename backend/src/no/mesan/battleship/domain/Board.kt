package no.mesan.battleship.domain

data class Board(val board: Array<IntArray>) {
    companion object {
        fun empty(size: Int) = Board(Array(size, { IntArray(size, { 0 }) }))
    }
}