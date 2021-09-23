package mytest.tictactoe.data.source.db

import androidx.room.*
import mytest.tictactoe.data.source.entity.PlayerEntity

/**
 * Data Access Object for the [PlayerEntity] class.
 */
@Dao
interface PlayersDao {
    /**
     * Select all players from the players table.
     *
     * @return all players.
     */
    @Query("SELECT * FROM players")
    suspend fun getAll(): List<PlayerEntity>

    @Query("SELECT * FROM players WHERE playerId IN (:playerIds)")
    suspend fun loadAllByIds(playerIds: IntArray): List<PlayerEntity>

    @Query("SELECT * FROM players WHERE name LIKE :name")
    suspend fun findAllByName(name: String): List<PlayerEntity>

    @Query("SELECT * FROM players WHERE name = :name")
    suspend fun findPlayerByName(name: String): PlayerEntity?

    /**
     * Insert a list of Player. If a player already exist, replace it.
     *
     * @param players list of Player to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg players: PlayerEntity)

    @Delete
    suspend fun delete(user: PlayerEntity)
}