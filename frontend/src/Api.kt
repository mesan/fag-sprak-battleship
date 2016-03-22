package no.mesan.battleship

import no.mesan.battleship.domain.*
import no.mesan.http.get
import no.mesan.http.post

val baseUrl = "127.0.0.1:8374";

fun newGame(username: String, ships: List<Ship>, callback: (AwaitingGame) -> Unit) {
    post<String>("$baseUrl/newGame/{$username}", JSON.stringify(ships)) {
        response -> callback(JSON.parse<AwaitingGame>(response))
    }
}

fun pollGame(gameId: Int, username: String, callback: ((PlayerAwareGame?) -> Unit)) {
    get<String>("$baseUrl/poll/{$gameId}") {
        response ->
        val game = JSON.parse<Game?>(response);
        if (game == null) {
            callback(null)
        } else {
            callback(toPlayerAwareGame(game, username))
        }
    }
}

fun shootGame(gameId: Int, username: String, target: Coordinate, callback: (Game?) -> Unit) {
    post<String>("$baseUrl/hit/{$gameId}/{$username}", JSON.stringify(target)) {
        response -> callback(JSON.parse<Game?>(response))
    }
}

fun toPlayerAwareGame(game: Game, username: String): PlayerAwareGame {
    val reverse = username != game.player1
    return PlayerAwareGame(
            game.gameId,
            if (reverse) game.player2 else game.player1,
            if (reverse) game.player1 else game.player2,
            if (reverse) game.player2Board else game.player1Board,
            if (reverse) game.player1Board else game.player2Board,
            reverse && game.winner == username,
            reverse && game.winner != username,
            reverse xor game.turn)
}