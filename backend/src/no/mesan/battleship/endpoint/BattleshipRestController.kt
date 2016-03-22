package no.mesan.battleship.endpoint

import no.mesan.battleship.domain.AwaitingGame
import no.mesan.battleship.domain.Coordinate
import no.mesan.battleship.domain.Game
import no.mesan.battleship.domain.Ship
import no.mesan.battleship.service.BattleshipService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class BattleshipRestController @Autowired constructor(val service: BattleshipService) {

    @RequestMapping(
            consumes = arrayOf("application/json"),
            method = arrayOf(RequestMethod.POST),
            value = "/newGame/{username}")
    fun newGame(@PathVariable username: String,
                @RequestBody ships: List<Ship>): AwaitingGame {
        return service.newGame(username, ships)
    }

    @RequestMapping(value = "/poll/{gameId}")
    fun pollGame(@PathVariable gameId: Int): Game? {
        return service.pollGame(gameId)
    }

    @RequestMapping(
            consumes = arrayOf("application/json"),
            method = arrayOf(RequestMethod.POST),
            value = "/hit/{gameId}/{username}")
    fun hit(@PathVariable gameId: Int,
            @PathVariable username: String,
            @RequestBody coordinate: Coordinate): Game {
        return service.hit(gameId, username, coordinate)
    }

    @RequestMapping(value = "/isCompleted/{gameId}")
    fun isCompleted(@PathVariable gameId: Int): Boolean {
        return service.isCompleted(gameId)
    }

    @RequestMapping(value = "/getWinner/{gameId}")
    fun getWinner(@PathVariable gameId: Int): String? {
        return service.getWinner(gameId)
    }

}