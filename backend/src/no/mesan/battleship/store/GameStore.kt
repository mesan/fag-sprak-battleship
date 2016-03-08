package no.mesan.battleship.store

import no.mesan.battleship.domain.AwaitingGame
import no.mesan.battleship.domain.Board
import no.mesan.battleship.domain.Game
import no.mesan.battleship.domain.Ship
import no.mesan.battleship.extensions.minus

class GameStore private constructor(private val games: Map<Int, Game>) {

    constructor() : this(mapOf())

    fun new(awaitingGame: AwaitingGame, player2: String, player2Ships: List<Ship>): Pair<Game, GameStore> {
        val game = Game(awaitingGame.gameid, awaitingGame.player1, player2,
                awaitingGame.player1Board,
                Board.empty(awaitingGame.xSize, awaitingGame.ySize).withShips(player2Ships),
                true)

        return (game to GameStore(games + (awaitingGame.gameid to game)))
    }

    fun update(game: Game): GameStore = GameStore(games + (game.gameId to game))

    operator fun get(gameId: Int): Game? = games[gameId]

    fun remove(gameId: Int): GameStore {
        if (games.containsKey(gameId)) {
            return GameStore(games - gameId)
        }

        return this
    }

}