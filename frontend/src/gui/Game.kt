package no.mesan.battleship.gui

import net.yested.Component
import net.yested.div
import no.mesan.battleship.api.pollGame
import no.mesan.battleship.api.shootGame
import no.mesan.battleship.domain.AwaitingGame
import no.mesan.battleship.domain.Board
import no.mesan.battleship.domain.Coordinate
import no.mesan.battleship.domain.PlayerAwareGame
import kotlin.browser.window

fun startGame(awaitingGame: AwaitingGame) {
    pollGame(awaitingGame.gameId, awaitingGame.player1) {
        game -> renderGame(game)
        if (!game.myVictory && !game.otherVictory) {
            window.setTimeout({
                startGame(awaitingGame)
            }, 1000)
        }
    }
}

fun renderGame(game: PlayerAwareGame) {
    val otherBoard = buildBoard(game.otherBoard){
        coordinate -> shootGame(game.gameId, game.myName, coordinate) {
            // TODO?
        }
    }

    val myBoard = buildBoard(game.myBoard) {
        coordinate -> // Nothing
    }

    val page = div {
        +otherBoard
        +myBoard
    }

    display(page)
}

private fun buildBoard(board: Board, onClick: (Coordinate) -> Unit): Component {
    val size = 8 // TODO: Softcode
    return div (clazz = "table") {
        for (row in 0 until size) {
            div(clazz = "row") {
                for (col in 0 until size) {
                    val cell = board.board[row][col]
                    div(clazz = "cell") {
                        onclick = {
                            onClick(Coordinate(row, col))
                        }
                        val text = cell.shipId ?: ""
                        +"$text" // TODO
                    }
                }
            }
        }
    }
}