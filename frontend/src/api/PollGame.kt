package no.mesan.battleship.api

import no.mesan.battleship.domain.Game
import no.mesan.battleship.domain.PlayerAwareGame
import no.mesan.http.get

fun pollGame(gameId: Int, username: String, callback: (PlayerAwareGame?) -> Unit) {
    get<Game?>("$serverUrl/poll/$gameId") {
        game ->
            println("Poll")
            println(game)
            if (game?.gameId == null)
                callback(null)
            else
                callback(toPlayerAwareGame(game!!, username))
    }
}