package no.mesan.battleship.store

import no.mesan.battleship.domain.Game
import no.mesan.battleship.domain.Ship
import no.mesan.battleship.extensions.minus

class GameStore private constructor(private val games: Map<Int, Game>) {

    private var gameId = 0

    constructor() : this(mapOf())

    fun new(player1: String, player2: String,
            player1Ships: List<Ship>, player2Ships: List<Ship>,
            xSize: Int, ySize: Int): Pair<Game, GameStore> {

        val id = gameId
        val game = Game(id, player1, player2, player1Ships, player2Ships, xSize, ySize)
        gameId++
        return (game to GameStore(games + (id to game)))

    }

    fun update(game: Game): GameStore = GameStore(games + (game.gameId to game))

    fun get(gameId: Int): Game? = games[gameId]

    fun remove(gameId: Int): GameStore {
        if(games.containsKey(gameId)) {
           return GameStore(games - gameId)
        }

        return this
    }

}