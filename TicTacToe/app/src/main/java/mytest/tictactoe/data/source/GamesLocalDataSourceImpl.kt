package mytest.tictactoe.data.source

import mytest.tictactoe.data.source.db.GamesDao
import mytest.tictactoe.result.Result
import mytest.tictactoe.data.source.mapper.GameMapper
import mytest.tictactoe.domain.model.Game
import mytest.tictactoe.result.ErrorType
import javax.inject.Inject

class GamesLocalDataSourceImpl @Inject constructor(
    private val gamesDao: GamesDao,
    private val gameMapper: GameMapper
): GamesLocalDataSource{

    override suspend fun getGames(): Result<List<Game>> {
        return try{
            Result.Success(
                gameMapper.mapFromEntityList(gamesDao.getAll())
            )
        }catch(e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun getGame(id: Long): Result<Game> {
        return try{
            val gameEntity = gamesDao.findGame(id.toString())
            if(gameEntity != null){
                Result.Success(gameMapper.mapFromEntity(gameEntity))
            }else{
                Result.Error(Exception("Game not found"), ErrorType.NO_RESULTS_FOUND)
            }
        }catch(e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun insertGame(game: Game): Result<Long> {
        return try{
            Result.Success( gamesDao.insertGame(gameMapper.mapToEntity(game)))
        }catch(e: Exception){
            Result.Error(e)
        }
    }

    override suspend fun updateGame(game: Game) {
        try{
            Result.Success( gamesDao.updateGame(gameMapper.mapToEntity(game)))
        }catch(e: Exception){
            Result.Error(e)
        }
    }

}