package mytest.tictactoe.util

import mytest.tictactoe.data.source.entity.PlayerEntity
import mytest.tictactoe.domain.model.Game
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

    val playerEmptyEntity = PlayerEntity( name = "")
    val player1Entity = PlayerEntity( playerId = 1, name = "player 1")
    val player2Entity = PlayerEntity( playerId = 2, name = "player 2")

    val playersEntity = listOf(
        player1,
        player2
    )

    val game = Game()
}