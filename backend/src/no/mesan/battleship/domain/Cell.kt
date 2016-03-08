package no.mesan.battleship.domain

data class Cell(val id: Int, val isHit: Boolean = false, val isOccupied: Boolean = false) {

    fun hit() = if (isHit) this else Cell(id, true, isOccupied)

}