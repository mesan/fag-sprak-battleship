package no.mesan.battleship.service

import no.mesan.battleship.domain.AwaitingGame
import no.mesan.battleship.domain.Coordinate
import no.mesan.battleship.domain.Game
import no.mesan.battleship.domain.Ship
import org.springframework.stereotype.Service

@Service
class BattleshipServiceImpl : BattleshipService {

    override fun newGame(username: String, ships: List<Ship>): AwaitingGame = throw NotImplementedError()

    override fun pollGame(gameId: Int): Game? = throw NotImplementedError()

    override fun hit(gameId: Int, coordinate: Coordinate): Game? = throw NotImplementedError()

    override fun isCompleted(gameId: Int): Boolean = throw NotImplementedError()

    override fun getWinner(gameId: Int): String? = throw NotImplementedError()

    override fun toString() = "Hei"

}
