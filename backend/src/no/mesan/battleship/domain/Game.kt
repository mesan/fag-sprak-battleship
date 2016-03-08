package no.mesan.battleship.domain

class Game(val gameId: Int, val player1: String, val player2: String,
           private val player1Board: Board, private val player2Board: Board,
           private val turn: Boolean) {

    fun isCompleted(): Boolean = player1Board.isCompleted() || player2Board.isCompleted()

    fun getWinner(): String? {
        if (player1Board.isCompleted()) {
            return player2
        } else if (player2Board.isCompleted()) {
            return player1
        } else {
            return null
        }
    }

    fun hit(square: Coordinate): Game {
        if (isCompleted()) {
            throw IllegalStateException("Cannot perform hit() on a completed game.")
        }

        val (newPlayer1, newPlayer2) =
                if (turn) {
                    Pair(player1Board.hit(square), player2Board)
                } else {
                    Pair(player1Board, player2Board.hit(square))
                }

        return Game(gameId, player1, player2, newPlayer1, newPlayer2, !turn)
    }


}