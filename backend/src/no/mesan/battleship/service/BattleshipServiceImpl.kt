package no.mesan.battleship.service

import no.mesan.battleship.domain.AwaitingGame
import no.mesan.battleship.domain.Coordinate
import no.mesan.battleship.domain.Game
import no.mesan.battleship.domain.Ship
import no.mesan.battleship.store.GameStore
import org.springframework.stereotype.Service

@Service
class BattleshipServiceImpl : BattleshipService {

    val boardSize = 8

    var store: GameStore = GameStore()
    var waiting: AwaitingGame? = null

    private var gameId = 0

    override fun newGame(username: String, ships: List<Ship>): AwaitingGame {
        val awaiting = waiting

        if (awaiting == null) {
            val game = AwaitingGame(gameId++, username, ships, boardSize, boardSize)
            waiting = AwaitingGame(gameId++, username, ships, boardSize, boardSize)
            return game
        } else {
            val gameStore = store.new(awaiting, username, ships)
            store = gameStore
            return AwaitingGame(awaiting.gameId, username, ships, boardSize, boardSize)
        }
    }

    override fun pollGame(gameId: Int): Game? = store[gameId]

    override fun hit(gameId: Int, player: String, coordinate: Coordinate): Game? = store[gameId]?.hit(coordinate, player)

    override fun isCompleted(gameId: Int): Boolean = store[gameId]?.isCompleted() ?: false

    override fun getWinner(gameId: Int): String? = store[gameId]?.getWinner()

}
