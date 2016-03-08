package no.mesan.battleship.service

import no.mesan.battleship.domain.AwaitingGame
import no.mesan.battleship.domain.Coordinate
import no.mesan.battleship.domain.Game
import no.mesan.battleship.domain.Ship

interface BattleshipService {

    fun newGame(username: String, ships: List<Ship>): AwaitingGame

    fun pollGame(gameId: Int): Game?

    fun hit(gameId: Int, coordinate: Coordinate): Game?

    fun isCompleted(gameId: Int): Boolean

    fun getWinner(gameId: Int): String?

}