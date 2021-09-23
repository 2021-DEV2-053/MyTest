package mytest.tictactoe.data.source.db

import androidx.room.*
import mytest.tictactoe.data.source.entity.GameEntity

/**
 * Data Access Object for the [GameEntity] class.
 */
@Dao
interface GamesDao {

    @Query("SELECT * FROM games")
    suspend fun getAll(): List<GameEntity>

    @Query("SELECT * FROM games WHERE id = :id")
    suspend fun findGame(id: String): GameEntity?

    @Insert
    suspend fun insertGame(game: GameEntity)

    @Update
    suspend fun updateGame(game: GameEntity)

}