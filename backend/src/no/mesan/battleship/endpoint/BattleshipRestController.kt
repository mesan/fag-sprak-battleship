package no.mesan.battleship.endpoint

import no.mesan.battleship.domain.Board
import no.mesan.battleship.domain.GameId
import no.mesan.battleship.domain.Ship
import no.mesan.battleship.domain.Username
import org.springframework.web.bind.annotation.*

@RestController
class BattleshipRestController {

    @RequestMapping("/emptyBoard")
    fun emptyBoard(@RequestParam(value = "size", defaultValue = "10") size: Int): Board {
        return Board.empty(size)
    }

    @RequestMapping(
            consumes = arrayOf("application/json"),
            method = arrayOf(RequestMethod.POST),
            value = "/newGame")
    fun newGame(@RequestParam(value = "username", required = false) username: Username?,
                @RequestBody ships: List<Ship>): Username {
        println(ships)
        return username ?: Username("Hei")
    }

    @RequestMapping(value = "gameState")
    fun getGameState(@RequestParam(value = "id", required = true) id: GameId): Board? {
        return null
    }

}