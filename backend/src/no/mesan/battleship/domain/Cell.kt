package no.mesan.battleship.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

data class Cell(@JsonIgnore val coordinate: Coordinate, val shipId: Int? = null, @JsonProperty("isHit") val isHit: Boolean = false) {

    val isOccupied: Boolean
        @JsonIgnore get() = shipId != null

    fun hit() = if (isHit) this else Cell(coordinate, shipId, true)

}