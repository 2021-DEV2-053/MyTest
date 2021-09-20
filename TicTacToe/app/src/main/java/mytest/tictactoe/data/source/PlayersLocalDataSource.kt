package mytest.tictactoe.data.source

import mytest.tictactoe.result.Result
import mytest.tictactoe.domain.model.Player

interface PlayersLocalDataSource{
    suspend fun getPlayers(): Result<List<Player>>
    suspend fun insertPlayers(vararg players: Player): Result<Boolean>
}