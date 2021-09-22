package mytest.tictactoe.util

import mytest.tictactoe.domain.model.Player

/**
 * Test data for unit tests.
 */
object TestData {

    val playerEmpty = Player( name = "")

    val player1 = Player( name = "player 1")
    val player2 = Player( name = "player 2")

    val players = listOf(
        player1,
        player2
    )
}