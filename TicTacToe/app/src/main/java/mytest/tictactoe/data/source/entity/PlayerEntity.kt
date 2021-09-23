package mytest.tictactoe.data.source.entity

import androidx.room.*

@Entity(tableName = "players", indices = [Index(value = ["name"], unique = true)])
data class PlayerEntity(
        @PrimaryKey(autoGenerate = true)
        val playerId : Long? = null,
        @ColumnInfo(name = "name")
        val name: String?
)