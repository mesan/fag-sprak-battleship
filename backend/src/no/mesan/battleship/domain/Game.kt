package no.mesan.battleship.domain

class Game(val gameId: Int, val player1: String, val player2: String,
           val player1Board: Board, val player2Board: Board,
           private val turn: Boolean) {

    fun isCompleted(): Boolean = player1Board.isCompleted() || player2Board.isCompleted()

    fun getWinner(): String? {
        if (player1Board.isCompleted() && player2Board.isCompleted()) {
            return "Draw";
        } else if (player1Board.isCompleted()) {
            return player2
        } else if (player2Board.isCompleted()) {
            return player1
        } else {
            return null
        }
    }

    fun hit(coordinate: Coordinate, player: String): Game {
        if (isCompleted()) {
            throw IllegalStateException("Cannot perform hit() on a completed game.")
        }

        val (newPlayer1, newPlayer2) =
                if (turn) {
                    require(player == player1, { "${player1} tried to hit on the opponents turn." })
                    Pair(player1Board, player2Board.hit(coordinate))
                } else {
                    require(player == player2, { "${player2} tried to hit on the opponents turn." })
                    Pair(player1Board.hit(coordinate), player2Board)
                }

        return Game(gameId, player1, player2, newPlayer1, newPlayer2, !turn)
    }


}