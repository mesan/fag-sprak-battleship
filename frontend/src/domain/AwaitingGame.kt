package no.mesan.battleship.domain

@native interface AwaitingGame {
    val gameId: Int
    val player1: String
    val player1Board: Board
    val xSize: Int
    val ySize: Int
}