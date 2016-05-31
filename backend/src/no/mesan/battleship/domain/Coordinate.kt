package no.mesan.battleship.domain

import com.fasterxml.jackson.annotation.JsonProperty

data class Coordinate(@JsonProperty("x") val x: Int, @JsonProperty("y") val y: Int) {
    init {
        require(x >= 0, { "X-axis was negative (${x})" })
        require(y >= 0, { "Y-axis was negative (${y})" })
    }

    fun toPair() = x to y
}