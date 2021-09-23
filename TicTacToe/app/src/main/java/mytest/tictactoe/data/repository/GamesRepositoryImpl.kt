package mytest.tictactoe.data.repository

import mytest.tictactoe.data.source.GamesLocalDataSource
import mytest.tictactoe.domain.model.Game
import mytest.tictactoe.result.Result
import mytest.tictactoe.domain.repository.GamesRepository
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val gamesLocalDataSource: GamesLocalDataSource
): GamesRepository {

    override suspend fun getGames(): Result<List<Game>> {
        TODO("Not yet implemented")
    }

    override suspend fun startNewGame(playerX: String, playerO: String): Result<Long> {
        TODO("Not yet implemented")
    }


}