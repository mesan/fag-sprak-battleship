
import net.yested.Component
import net.yested.div
import net.yested.el

@native class GameState {
    val board : Array<IntArray>
}

data class Coordinate(var x:Int, var y:Int) {
}

fun main(args: Array<String>) {
    get<String>("test.txt") {
        response ->
        val gamestate = JSON.parse<GameState>(response)
        println(response)
        displayBoard(gamestate.board, null, null)
    }
}


fun displayBoard(board:Array<IntArray>, start:Coordinate?, current:Coordinate?) {
    val rows = board.size
    val cols = board[0].size

    val validSelection = isValidSelection(start, current);

    val test = div (clazz = "table") {
        for (row in 0 until rows) {
            div(clazz = "row") {
                for (col in 0 until cols) {
                    val cell = Coordinate(col, row);

                    var type = "";
                    if (validSelection && isInSelection(start, current, cell)) {
                        type = "middle"
                    }
                    if (cell == start) {
                        if (validSelection && current != null) {
                            if (cell.x < current.x) {
                                type = "left"
                            }
                            if (cell.x > current.x) {
                                type = "right"
                            }
                            if (cell.y < current.y) {
                                type = "up"
                            }
                            if (cell.y > current.y) {
                                type = "down"
                            }
                        } else {
                            type = "invalid"
                        }
                    }
                    if (cell == current) {
                        if (validSelection && start != null) {
                            if (cell.x < start.x) {
                                type = "left"
                            }
                            if (cell.x > start.x) {
                                type = "right"
                            }
                            if (cell.y < start.y) {
                                type = "up"
                            }
                            if (cell.y > start.y) {
                                type = "down"
                            }
                        } else {
                            type = "invalid"
                        }
                    }
                    div(clazz = "cell " + type) {
                        val value = board[row][col]
                        onmousedown = {
                            if (start == null) {
                                displayBoard(board, cell, cell)
                                println("Cell [$row,$col] down")
                            } else {
                                displayBoard(board, null, null)
                                println("Cell [$row,$col] up")
                            }
                        }
                        onmouseover = {
                            if (start != null && cell != current) {
                                displayBoard(board, start, cell)
                                println("Cell [$row,$col] over")
                            }
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

fun isValidSelection(start:Coordinate?, end:Coordinate?):Boolean {
    if (start == null || end == null) {
        return false;
    }
    return (start.y == end.y && start.x != end.x)
            || (start.x == end.x && start.y != end.y);
}

fun isInSelection(start:Coordinate?, end:Coordinate?, current:Coordinate):Boolean {
    if (start == null || end == null) {
        return false;
    }
    return current.y >= Math.min(start.y, end.y)
            && current.y <= Math.max(start.y, end.y)
            && current.x >= Math.min(start.x, end.x)
            && current.x <= Math.max(start.x, end.x);
}

fun setContent(n: Component) {
    el("app")?.innerHTML = ""
    el("app")?.appendChild(n.element)
}