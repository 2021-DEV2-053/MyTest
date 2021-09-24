package mytest.tictactoe.data.repository

import mytest.tictactoe.data.source.GamesLocalDataSource
import mytest.tictactoe.domain.model.Game
import mytest.tictactoe.domain.model.Player
import mytest.tictactoe.result.Result
import mytest.tictactoe.domain.repository.GamesRepository
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val gamesLocalDataSource: GamesLocalDataSource
): GamesRepository {

    override suspend fun getGames(): Result<List<Game>> {
        return gamesLocalDataSource.getGames()
    }

    override suspend fun startNewGame(playerX: Player, playerO: Player): Result<Long> {
        return gamesLocalDataSource.insertGame(
            Game(
                playerX = playerX.id,
                playerO = playerO.id
            )
        )
    }

    override suspend fun getTheNewGame(gameId: Long): Result<Game> {
        return gamesLocalDataSource.getGame(gameId)
    }

}