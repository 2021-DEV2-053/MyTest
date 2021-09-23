package mytest.tictactoe.domain.model

import java.util.*

data class Game(
    var gameId: Int? = null,
    val createdAt: Date? = null,
    val endedAt: Date? = null,
    val status: String? = null,
    val playerX: Player? = null,
    val playerO: Player? = null,
    val currentPlayer: Player? = null,
    val winningPlayer: Player? = null,
    val losingPlayer: Player? = null
)