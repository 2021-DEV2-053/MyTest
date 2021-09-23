package mytest.tictactoe.util

import mytest.tictactoe.data.source.GamesLocalDataSource
import mytest.tictactoe.domain.model.Game
import mytest.tictactoe.result.Result

class FakeGamesLocalDataSource: GamesLocalDataSource {

    override suspend fun getGames(): Result<List<Game>> {
        return Result.Success(TestData.games)
    }

    override suspend fun getGame(id: Long): Result<Game> {
        TODO("Not yet implemented")
    }

    override suspend fun insertGame(game: Game): Result<Long> {
        TODO("Not yet implemented")
    }
}