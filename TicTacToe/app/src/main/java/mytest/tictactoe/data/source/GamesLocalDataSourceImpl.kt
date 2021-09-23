package mytest.tictactoe.data.source

import mytest.tictactoe.data.source.db.GamesDao
import mytest.tictactoe.result.Result
import mytest.tictactoe.data.source.mapper.GameMapper
import mytest.tictactoe.domain.model.Game
import javax.inject.Inject

class GamesLocalDataSourceImpl @Inject constructor(
    private val gamesDao: GamesDao,
    private val gameMapper: GameMapper
): GamesLocalDataSource{

    override suspend fun getGames(): Result<List<Game>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertGame(game: Game): Result<Long> {
        TODO("Not yet implemented")
    }

}