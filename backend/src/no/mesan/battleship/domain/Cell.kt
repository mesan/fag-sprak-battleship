package no.mesan.battleship.domain

data class Cell(val id: Int, val shipId: Int? = null, val isHit: Boolean = false) {

    val isOccupied: Boolean
        get() = shipId != null

    fun hit() = if (isHit) this else Cell(id, shipId, true)

}