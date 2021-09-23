package mytest.tictactoe.data.source

import mytest.tictactoe.domain.model.Game
import mytest.tictactoe.result.Result
import mytest.tictactoe.domain.model.Player

interface GamesLocalDataSource{
    suspend fun getGames(): Result<List<Game>>
    suspend fun getGame(id: Long): Result<Game>
    suspend fun insertGame(game: Game): Result<Long>
}