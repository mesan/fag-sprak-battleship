package no.mesan.battleship.endpoint

import no.mesan.battleship.domain.Board
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BattleshipRestController {

    @RequestMapping("/emptyBoard")
    fun emptyBoard(@RequestParam(value = "size", defaultValue = "10") size: Int): Board {
        return Board.empty(size)
    }

}