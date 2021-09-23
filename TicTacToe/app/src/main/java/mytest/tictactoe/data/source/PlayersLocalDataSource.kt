package mytest.tictactoe.data.source

import mytest.tictactoe.result.Result
import mytest.tictactoe.domain.model.Player

interface PlayersLocalDataSource{
    suspend fun getPlayers(): Result<List<Player>>
    suspend fun getPlayerById(playerId: Long): Result<Player>
    suspend fun getPlayerByName(name: String): Result<Player>
    suspend fun getPlayersByNames(vararg playersName: String): Result<List<Player>>
    suspend fun insertPlayers(vararg players: Player): Result<List<Player>>
}