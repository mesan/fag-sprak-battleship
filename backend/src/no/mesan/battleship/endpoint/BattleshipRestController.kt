package no.mesan.battleship.endpoint

import no.mesan.battleship.domain.AwaitingGame
import no.mesan.battleship.domain.Board
import no.mesan.battleship.domain.Ship
import no.mesan.battleship.service.BattleshipService
import no.mesan.battleship.store.AwaitingGameStore
import no.mesan.battleship.store.GameStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class BattleshipRestController @Autowired constructor(val service: BattleshipService) {

    init {
        println(service.toString())
    }


    val boardSize = 8

    var store: GameStore = GameStore()
    var waiting: AwaitingGameStore = AwaitingGameStore()

    @RequestMapping("/emptyBoard")
    fun emptyBoard(@RequestParam(value = "size", defaultValue = "10") size: Int): Board {

        return Board.empty(size)
    }

    @RequestMapping(
            consumes = arrayOf("application/json"),
            method = arrayOf(RequestMethod.POST),
            value = "/newGame")
    fun newGame(@RequestParam(value = "username") username: String,
                @RequestBody ships: List<Ship>): AwaitingGame {
        val (game, waiting) = waiting.newGame(username, ships, boardSize, boardSize)
        return game
    }

    @RequestMapping(value = "/gameState/{id}")
    fun getGameState(@PathVariable id: Int): Board? {
        return null
    }

}