package mytest.tictactoe.domain.model

import java.util.*

data class Game(
    var gameId: Long? = null,
    val createdAt: Date? = Date(),
    val endedAt: Date? = null,
    val playerX: Long? = null,
    val playerO: Long? = null,
    val winningPlayer: Long? = null
)