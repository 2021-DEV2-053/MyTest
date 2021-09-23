package mytest.tictactoe.data.repository

import mytest.tictactoe.data.source.PlayersLocalDataSource
import mytest.tictactoe.result.Result
import mytest.tictactoe.domain.model.Player
import mytest.tictactoe.domain.repository.PlayersRepository
import mytest.tictactoe.result.ErrorType
import javax.inject.Inject

class PlayersRepositoryImpl @Inject constructor(
    private val playerLocalDataSource: PlayersLocalDataSource
): PlayersRepository {

    override suspend fun getPlayers(): Result<List<Player>> {
        return playerLocalDataSource.getPlayers()
    }

    override suspend fun insertPlayers(playerX: String, playerO: String): Result<List<Player>> {
        if(playerX.isNullOrBlank() && playerO.isNullOrBlank()){
            return Result.Error(Exception("Players name are empty"), ErrorType.ERROR_PLAYERS_NAME_EMPTY)
        }

        if(playerX.isNullOrBlank()){
            return Result.Error(Exception("PlayerX name is empty"), ErrorType.ERROR_PLAYER_X_EMPTY)
        }

        if(playerO.isNullOrBlank()){
            return Result.Error(Exception("PlayerO name is empty"), ErrorType.ERROR_PLAYER_O_EMPTY)
        }

        if(playerX == playerO){
            return Result.Error(Exception("Players have the same name"), ErrorType.ERROR_PLAYERS_CONFLICT_NAME)
        }

        return playerLocalDataSource.insertPlayers(Player(name = playerX), Player(name = playerO))

    }

}