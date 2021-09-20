package mytest.tictactoe.data.source.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import mytest.tictactoe.data.source.entity.PlayerEntity

/**
 * Data Access Object for the [PlayerEntity] class.
 */
@Dao
interface  PlayersDao {
    @Query("SELECT * FROM players")
    fun getAll(): List<PlayerEntity>

    @Query("SELECT * FROM players WHERE id IN (:playerIds)")
    fun loadAllByIds(playerIds: IntArray): List<PlayerEntity>

    @Query("SELECT * FROM players WHERE name LIKE :name")
    fun findAllByName(name: String): List<PlayerEntity>

    @Insert
    fun insertAll(vararg players: PlayerEntity)

    @Delete
    fun delete(user: PlayerEntity)
}