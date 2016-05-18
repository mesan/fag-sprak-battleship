package no.mesan.battleship.api

import no.mesan.battleship.domain.Coordinate
import no.mesan.battleship.domain.Game
import no.mesan.battleship.domain.PlayerAwareGame
import no.mesan.http.post

fun shootGame(gameId: Int, username: String, target: Coordinate, callback: (PlayerAwareGame) -> Unit) {
    post<Game>("$serverUrl/hit/$gameId/$username", JSON.stringify(target)) {
        game -> callback(toPlayerAwareGame(game, username))
    }
}
