package no.mesan.battleship.service

import no.mesan.battleship.domain.AwaitingGame
import no.mesan.battleship.domain.Coordinate
import no.mesan.battleship.domain.Game
import no.mesan.battleship.domain.Ship
import no.mesan.battleship.store.GameStore
import org.springframework.stereotype.Service

@Service
class BattleshipServiceImpl : BattleshipService {

    private val boardSize: Int
        get() = 8

    private var store: GameStore = GameStore()
    private var waiting: AwaitingGame? = null

    private var gameId = 0

    override fun newGame(username: String, ships: List<Ship>): AwaitingGame {
        val awaiting = waiting

        if (awaiting == null) {
            val id = gameId++
            val game = AwaitingGame(id, username, ships, boardSize, boardSize)
            waiting = game
            return game
        } else {
            val gameStore = store.new(awaiting, username, ships)
            store = gameStore
            waiting = null
            return AwaitingGame(awaiting.gameId, username, ships, boardSize, boardSize)
        }
    }

    override fun pollGame(gameId: Int): Game? = store[gameId]

    override fun hit(gameId: Int, player: String, coordinate: Coordinate): Game {
        val game = store[gameId]?.hit(coordinate, player) ?: throw IllegalArgumentException("Game $gameId does not exist.")
        store = store.update(game)
        return game
    }

    override fun isCompleted(gameId: Int): Boolean = store[gameId]?.isCompleted() ?: false

    override fun getWinner(gameId: Int): String? = store[gameId]?.getWinner()

}