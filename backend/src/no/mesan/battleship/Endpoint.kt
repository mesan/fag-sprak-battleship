package no.mesan.battleship

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class Endpoint {

    @RequestMapping("/emptyBoard")
    fun emptyBoard(@RequestParam(value = "size", defaultValue = "10") size: Int): Board {
        return Board.empty(size)
    }

}

data class Board(val board: Array<IntArray>) {
    companion object {
        fun empty(size: Int) = Board(Array<IntArray>(size, { IntArray(size, { 0 }) }))
    }
}