package mytest.tictactoe.util

import mytest.tictactoe.data.source.entity.PlayerEntity
import mytest.tictactoe.domain.model.Game
import mytest.tictactoe.domain.model.Player

/**
 * Test data for unit tests.
 */
object TestData {

    /*****Test data for PLayer repo*********/
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

    /*****Test data for GAME repo*********/
    val playerX = Player( id= 1, name = "player 1")
    val playerO = Player( id= 2, name = "player 2")

    val game = Game(gameId = 1, playerX = playerX, playerO = playerO  )

    val game1 = Game(gameId = 1, playerX = playerX, playerO = playerO  )
    val game2 = Game(gameId = 2, playerX = playerX, playerO = playerO  )

    val games = listOf(
        game1,
        game2
    )
}