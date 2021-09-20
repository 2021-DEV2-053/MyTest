package mytest.tictactoe.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import mytest.tictactoe.data.source.entity.PlayerEntity

@Database(
        entities = arrayOf(PlayerEntity::class),
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playersDao(): PlayersDao
}