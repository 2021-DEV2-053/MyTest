package mytest.tictactoe.data.source

import mytest.tictactoe.result.Result
import mytest.tictactoe.data.source.db.PlayersDao
import mytest.tictactoe.data.source.mapper.PlayerMapper
import mytest.tictactoe.domain.model.Player
import mytest.tictactoe.result.ErrorType
import javax.inject.Inject

class PlayersLocalDataSourceImpl @Inject constructor(
    private val playerDao: PlayersDao,
    private val playerMapper: PlayerMapper
): PlayersLocalDataSource{

    override suspend fun getPlayers(): Result<List<Player>> {
        return try{
            Result.Success(
                playerMapper.mapFromEntityList(playerDao.getAll())
            )
        }catch(e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun getPlayerById(playerId: Long): Result<Player> {
        return try{
            val playerEntity = playerDao.findPlayerById(playerId)
            if(playerEntity != null){
                Result.Success(playerMapper.mapFromEntity(playerEntity)!!)
            }else{
                Result.Error(Exception("Player not found"), ErrorType.NO_RESULTS_FOUND)
            }
        }catch(e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun getPlayerByName(name: String): Result<Player> {
        return try{
            val playerEntity = playerDao.findPlayerByName(name)
            if(playerEntity != null){
                Result.Success(playerMapper.mapFromEntity(playerEntity)!!)
            }else{
                Result.Error(Exception("Player not found"), ErrorType.NO_RESULTS_FOUND)
            }
        }catch(e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun getPlayersByNames(vararg playersName: String): Result<List<Player>> {
        return try{
            val players = ArrayList<Player>()
            playersName.forEach { name ->
                val playerEntity = playerDao.findPlayerByName(name)
                if(playerEntity != null){
                    players.add(playerMapper.mapFromEntity(playerEntity)!!)
                }
            }
            Result.Success(players)
        }catch(e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun insertPlayers(vararg players: Player): Result<List<Player>> {
        return try{
            val listOfplayerEntity = playerMapper.mapToEntityList(players.toList())
            val playersIds = playerDao.insertAll(*listOfplayerEntity.toTypedArray())
            Result.Success(playerMapper.mapFromEntityList(playerDao.loadAllByIds(playersIds.toLongArray())))
        }catch(e: Exception){
            Result.Error(e)
        }
    }

}