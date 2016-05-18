package no.mesan.battleship.domain

@native interface Board {
    val board: Array<Array<Cell>>
}