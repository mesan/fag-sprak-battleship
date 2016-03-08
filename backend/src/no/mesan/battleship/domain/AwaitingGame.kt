package no.mesan.battleship.domain

data class AwaitingGame private constructor(val gameid: Int, val player1: String, val player1Board: Board, val xSize: Int, val ySize: Int) {

    // New game constructor
    constructor(gameId: Int, player1: String, player1Ships: List<Ship>, xSize: Int, ySize: Int)
    : this(gameId, player1, Board.empty(xSize, ySize).withShips(player1Ships), xSize, ySize)

}