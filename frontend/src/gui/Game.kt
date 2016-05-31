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
        game ->
        window.setTimeout({
            startGame(awaitingGame)
        }, 1000)
        if (game != null) {
            renderGame(game)
        }
    }
}

fun renderGame(game: PlayerAwareGame) {
    println("Render")
    println(game)
    val otherBoard = buildOtherBoard(game.otherBoard){
        coordinate -> shootGame(game.gameId, game.myName, coordinate) {
            // TODO?
        }
    }

    val myBoard = buildMyBoard(game.myBoard)

    val page = div {
        if (game.myVictory) {
            +div(clazz="text") {
                +"You won :D"
            }
        }
        if (game.otherVictory) {
            +div(clazz="text") {
                +"You lost :("
            }
        }
        +otherBoard
        +myBoard
    }

    display(page)
}

private fun buildOtherBoard(board: Board, onClick: (Coordinate) -> Unit): Component {
    val size = 8 // TODO: Softcode
    return div (clazz = "table") {
        for (row in 0 until size) {
            div(clazz = "row") {
                for (col in 0 until size) {
                    val cell = board.board[row][col]
                    val hittype =
                            if (cell.hit)
                                if (cell.shipId != null) "hit"
                                else "miss"
                            else "none"
                    div(clazz = "cell $hittype") {
                        onclick = {
                            onClick(Coordinate(row, col))
                        }
                        // TODO?
                    }
                }
            }
        }
    }
}

private fun buildMyBoard(board: Board): Component {
    val size = 8 // TODO: Softcode
    return div (clazz = "table") {
        for (row in 0 until size) {
            div(clazz = "row") {
                for (col in 0 until size) {
                    val cell = board.board[row][col]
                    val hittype =
                            if (cell.hit)
                                if (cell.shipId != null) "hit"
                                else "miss"
                            else
                                if (cell.shipId != null) "middle"
                                else "none"
                    div(clazz = "cell $hittype") {
                        // TODO?
                    }
                }
            }
        }
    }
}