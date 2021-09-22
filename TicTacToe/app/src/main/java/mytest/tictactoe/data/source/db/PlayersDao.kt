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
    fun getAll(): List<PlayerEntity>

    @Query("SELECT * FROM players WHERE id IN (:playerIds)")
    fun loadAllByIds(playerIds: IntArray): List<PlayerEntity>

    @Query("SELECT * FROM players WHERE name LIKE :name")
    fun findAllByName(name: String): List<PlayerEntity>

    @Query("SELECT * FROM players WHERE name = :name")
    fun findPlayerByName(name: String): PlayerEntity

    /**
     * Insert a list of Player. If a player already exist, replace it.
     *
     * @param players list of Player to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg players: PlayerEntity)

    @Delete
    fun delete(user: PlayerEntity)
}