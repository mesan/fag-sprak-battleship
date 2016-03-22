package no.mesan.battleship

import no.mesan.battleship.domain.AwaitingGame
import no.mesan.battleship.domain.Coordinate
import no.mesan.battleship.domain.Game
import no.mesan.battleship.domain.Ship
import no.mesan.http.get
import no.mesan.http.post

val baseUrl = "127.0.0.1:8374";

fun newGame(username: String, ships: List<Ship>, callback: (AwaitingGame) -> Unit) {
    post<String>("$baseUrl/newGame/{$username}", JSON.stringify(ships)) {
        response -> callback(JSON.parse<AwaitingGame>(response))
    }
}

fun pollGame(gameId: Int, callback: ((Game?) -> Unit)) {
    get<String>("$baseUrl/poll/{$gameId}") {
        response -> callback(JSON.parse<Game?>(response))
    }
}

fun shootGame(gameId: Int, username: String, target: Coordinate, callback: (Game?) -> Unit) {
    post<String>("$baseUrl/hit/{$gameId}/{$username}", JSON.stringify(target)) {
        response -> callback(JSON.parse<Game?>(response))
    }
}