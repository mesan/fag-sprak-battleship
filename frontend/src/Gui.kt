package no.mesan.battleship

import net.yested.Component
import net.yested.div
import net.yested.el
import no.mesan.battleship.domain.Board
import no.mesan.battleship.domain.PlayerAwareGame

fun renderGame(game: PlayerAwareGame) {
    val myBoard = buildBoard(game.myBoard) {
        x, y -> // TODO
    }
    val otherBoard = buildBoard(game.otherBoard){
        x, y -> // Nothing
    }

    val render = div {
        +myBoard
        +otherBoard
    }
    setContent(render)
}

fun buildBoard(board: Board, onClick: (x:Int, y:Int) -> Unit): Component {
    return div (clazz = "table") {
        for (row in board.board) {
            div(clazz = "row") {
                for (cell in row) {
                    div(clazz = "cell") {
                        onclick = {
                            onClick(0, 0) // TODO
                        }
                        val text = cell.shipId ?: ""
                        +"$text" // TODO
                    }
                }
            }
        }
    }
}

fun setContent(n: Component) {
    el("app")?.innerHTML = ""
    el("app")?.appendChild(n.element)
}