package mytest.tictactoe.data.repository

import mytest.tictactoe.data.source.PlayersLocalDataSource
import mytest.tictactoe.result.Result
import mytest.tictactoe.domain.model.Player
import mytest.tictactoe.domain.repository.PlayersRepository
import javax.inject.Inject

class PlayersRepositoryImpl @Inject constructor(
    private val playerLocalDataSource: PlayersLocalDataSource
): PlayersRepository {

    override suspend fun getPlayers(): Result<List<Player>> {
        return playerLocalDataSource.getPlayers()
    }

}