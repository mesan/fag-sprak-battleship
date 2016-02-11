package no.mesan.battleship.endpoint

import no.mesan.battleship.domain.Board
import no.mesan.battleship.domain.Ship
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
    fun newGame(@RequestParam(value = "username", required = false) username: String?,
                @RequestBody ships: List<Ship>): String {

        println(ships)

        return username ?: "Hei"
    }

}