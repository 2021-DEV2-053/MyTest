package mytest.tictactoe.data.source

import mytest.tictactoe.data.source.db.PlayersDao
import mytest.tictactoe.data.source.mapper.PlayerMapper
import mytest.tictactoe.domain.model.Player
import javax.inject.Inject

class PlayersLocalDataSourceImpl @Inject constructor(
    private val playerDao: PlayersDao,
    private val playerMapper: PlayerMapper
): PlayersLocalDataSource{

    override suspend fun getPlayers(): Result<List<Player>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertPlayers(vararg players: Player): Result<List<Player>> {
        TODO("Not yet implemented")
    }

}