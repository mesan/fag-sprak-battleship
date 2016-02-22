package no.mesan.battleship.dao

import no.mesan.battleship.domain.Game

class GameStore private constructor(private val games: Set<Game> = setOf()) {

    fun newGame(id: Int, player1: String, player2: String) =
            GameStore(games + Game(id, player1, player2))


    fun getGame(gameId: Int) = games.find { it.gameId == gameId }

    fun removeGame(gameId: Int): GameStore {
        return getGame(gameId)?.let {
            GameStore(games - it)
        } ?: this
    }

}