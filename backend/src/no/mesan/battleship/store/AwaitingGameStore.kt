package no.mesan.battleship.store

import no.mesan.battleship.domain.AwaitingGame
import no.mesan.battleship.domain.Ship
import no.mesan.battleship.extensions.minus

class AwaitingGameStore private constructor(private val games: Map<Int, AwaitingGame>) {

    private var gameId = 0

    constructor() : this(mapOf())

    fun newGame(player1: String, player1Ships: List<Ship>, xSize: Int, ySize: Int): Pair<AwaitingGame, AwaitingGameStore> {
        val id = gameId
        gameId++
        val game = AwaitingGame(gameId, player1, player1Ships, xSize, ySize)
        return game to AwaitingGameStore(games + (gameId to game))
    }

    fun get(gameId: Int): AwaitingGame? = games[gameId]

    fun remove(gameId: Int): AwaitingGameStore {
        if (games.containsKey(gameId)) {
            return AwaitingGameStore(games - gameId)
        }

        return this
    }

}