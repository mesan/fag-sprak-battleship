package no.mesan.battleship.domain

@native data class Cell(val shipId: Int? = null, val isHit: Boolean = false)