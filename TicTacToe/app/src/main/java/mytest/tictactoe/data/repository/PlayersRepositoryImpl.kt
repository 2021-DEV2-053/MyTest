package mytest.tictactoe.data.repository

import mytest.tictactoe.data.source.PlayersLocalDataSource
import mytest.tictactoe.result.Result
import mytest.tictactoe.domain.model.Player
import mytest.tictactoe.domain.repository.PlayersRepository
import mytest.tictactoe.result.succeeded
import javax.inject.Inject

class PlayersRepositoryImpl @Inject constructor(
    private val playerLocalDataSource: PlayersLocalDataSource
): PlayersRepository {

    override suspend fun getPlayers(): Result<List<Player>> {
        return playerLocalDataSource.getPlayers()
    }

    override suspend fun insertPlayers(vararg players: Player): Result<List<Player>> {
        val result = playerLocalDataSource.insertPlayers(*players)
        return if(result.succeeded){
            playerLocalDataSource.getPlayersByNames(*players)
        }else{
            Result.Error(
                Exception("No Players found")
            )
        }
    }

}