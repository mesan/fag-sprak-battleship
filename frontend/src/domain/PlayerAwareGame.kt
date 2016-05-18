package no.mesan.battleship.domain

data class PlayerAwareGame(
        val gameId: Int,
        val myName: String,
        val otherName: String,
        val myBoard: Board,
        val otherBoard: Board,
        val myVictory: Boolean,
        val otherVictory: Boolean,
        val myTurn: Boolean)