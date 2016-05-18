package no.mesan.battleship.gui

import net.yested.Component
import net.yested.TextInput
import net.yested.div
import no.mesan.battleship.api.newGame
import no.mesan.battleship.domain.Coordinate
import no.mesan.battleship.domain.Ship

private var username = ""

fun renderCreateGame(ships: List<Ship>, start: Coordinate? = null) {
    val drawingBoard = buildBoard(ships, start) {
        coordinate ->
        if (start != null) {
            try {
                val newShip = Ship(start, coordinate)
                renderCreateGame(ships + newShip)
            } catch (e: Exception) {
                // TODO: Not valid selection, do something?
            }
        } else {
            renderCreateGame(ships, coordinate)
        }
    }

    val usernameInput = TextInput()
    usernameInput.data = username
    usernameInput.addOnChangeLiveListener {
        username = usernameInput.data
    }

    val startButton = buildStartButton {
        newGame(username, ships) {
            awaitingGame -> startGame(awaitingGame)
        }
    }

    val page = div {
        +usernameInput
        +drawingBoard
        +startButton
    }

    display(page)
}

private fun buildBoard(ships: List<Ship>, start:Coordinate?, onClick: (Coordinate) -> Unit): Component {
    val size = 8 // TODO: Softcode
    return div (clazz = "table") {
        for (row in 0 until size) {
            div(clazz = "row") {
                for (col in 0 until size) {
                    val current = Coordinate(col, row)

                    var shipSection = "none"
                    for (ship in ships) {
                        shipSection = ship.sectionTypeAt(current) ?: shipSection
                    }

                    if (start == current) {
                        shipSection = "start";
                    }
                    /*
                    if (start != null) {
                        try {
                            val selection = Ship(start, current)
                            shipSection = selection.sectionTypeAt(current) ?: shipSection
                        } catch (e: Exception) {
                            // TODO: Not valid selection, display something?
                        }
                    }*/

                    div(clazz = "cell $shipSection") {
                        onclick = {
                            if (shipSection == "none") {
                                onClick(current)
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun buildStartButton(onClick:() -> Unit): Component {
    return div (clazz = "button") {
        +"Start"
        onclick = {
            onClick()
        }
    }
}