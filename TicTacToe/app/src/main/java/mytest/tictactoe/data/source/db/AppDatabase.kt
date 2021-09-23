package mytest.tictactoe.data.source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mytest.tictactoe.data.source.entity.PlayerEntity
import mytest.tictactoe.data.util.DateConverter

@Database(
        entities = arrayOf(PlayerEntity::class),
        version = 1,
        exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playersDao(): PlayersDao
}