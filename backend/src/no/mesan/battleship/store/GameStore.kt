package no.mesan.battleship.store

import no.mesan.battleship.domain.Game
import no.mesan.battleship.extensions.minus

class GameStore private constructor(private val games: Map<Int, Game>) {

    constructor(): this(mapOf())

    fun new(id: Int, player1: String, player2: String): GameStore {
        return GameStore(games + (id to Game(id, player1, player2)))
    }

    fun update(game: Game): GameStore = GameStore(games + (game.gameId to game))

    fun getGame(gameId: Int): Game? = games.get(gameId)

    fun remove(gameId: Int): GameStore {
        return getGame(gameId)?.let {
            GameStore(games - gameId)
        } ?: this
    }

}