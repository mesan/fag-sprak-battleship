package no.mesan.battleship.api

import no.mesan.battleship.domain.Game
import no.mesan.battleship.domain.PlayerAwareGame

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