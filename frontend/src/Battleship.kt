
import net.yested.Component
import net.yested.div
import net.yested.el

@native class GameState {
    val board : Array<IntArray>
}

fun main(args: Array<String>) {
    get<String>("test.txt") {
        response ->
        val gamestate = JSON.parse<GameState>(response)
        println(response)
        displayBoard(gamestate.board)
    }
}

fun displayBoard(board:Array<IntArray>) {
    val rows = board.size
    var cols = board[0].size

    val test = div (clazz = "table") {
        for (row in 0 until rows) {
            div (clazz = "row"){
                for (col in 0 until cols) {
                    div(clazz = "cell") {
                        val value = board[row][col]
                        onmousedown = {
                            println("Cell [$row,$col] contains $value")
                        }
                        +"$value"
                    }
                }
            }
        }
    }

    setContent(test)
    println("Displayed board")
}

fun setContent(n: Component) {
    el("app")?.innerHTML = ""
    el("app")?.appendChild(n.element)
}