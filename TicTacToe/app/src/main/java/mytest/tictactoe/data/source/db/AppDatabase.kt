package mytest.tictactoe.data.source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import mytest.tictactoe.data.source.entity.PlayerEntity

@Database(
        entities = arrayOf(PlayerEntity::class),
        version = 1,
        exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun playersDao(): PlayersDao


    companion object {
        private const val databaseName = "tictactoe-db"

        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}