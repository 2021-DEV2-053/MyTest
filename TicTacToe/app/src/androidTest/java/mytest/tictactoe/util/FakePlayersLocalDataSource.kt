package mytest.tictactoe.util

import mytest.tictactoe.data.source.PlayersLocalDataSource
import mytest.tictactoe.domain.model.Player
import mytest.tictactoe.result.Result

class FakePlayersLocalDataSource: PlayersLocalDataSource {

    override suspend fun getPlayers(): Result<List<Player>> {
        return Result.Success(TestData.players)
    }

    override suspend fun getPlayerByName(name: String): Result<Player> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlayersByNames(vararg playersName: String): Result<List<Player>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertPlayers(vararg players: Player): Result<List<Long>> {
        TODO("Not yet implemented")
    }
}