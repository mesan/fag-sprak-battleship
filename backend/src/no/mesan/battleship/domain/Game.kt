package no.mesan.battleship.domain

class Game private constructor(val gameId: Int, val player1: String, val player2: String,
                               private val player1Board: Board, private val player2Board: Board,
                               private val turn: Boolean) {

    // New game constructor
    constructor(gameId: Int, player1: String, player2: String, player1Ships: List<Ship>, player2Ships: List<Ship>, xSize: Int, ySize: Int)
    : this(gameId, player1, player2, Board.empty(xSize, ySize).withShips(player1Ships), Board.empty(xSize, ySize).withShips(player2Ships), true)

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