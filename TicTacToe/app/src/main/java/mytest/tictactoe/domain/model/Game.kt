package mytest.tictactoe.domain.model

import java.util.*

data class Game(
    var id: Int? = null,
    val createdAt: Date?,
    val endedAt: Date?,
    val status: String?,
    val playerX: Player?,
    val playerO: Player?,
    val currentPlayer: Player?,
    val winningPlayer: Player?,
    val losingPlayer: Player?
)