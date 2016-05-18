package no.mesan.battleship.domain

@native interface Game {
        val gameId: Int
        val player1: String
        val player2: String
        val player1Board: Board
        val player2Board: Board
        val turn: Boolean
        val winner: String
}