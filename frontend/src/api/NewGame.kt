package no.mesan.battleship.api

import no.mesan.battleship.domain.AwaitingGame
import no.mesan.battleship.domain.Ship
import no.mesan.http.post

fun newGame(username: String, ships: List<Ship>, callback: (AwaitingGame) -> Unit) {
    post<AwaitingGame>("$serverUrl/newGame/$username", JSON.stringify(ships)) {
        awaitingGame -> callback(awaitingGame)
    }
}