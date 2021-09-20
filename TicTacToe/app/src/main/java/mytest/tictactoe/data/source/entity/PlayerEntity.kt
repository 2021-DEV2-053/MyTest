package mytest.tictactoe.data.source.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "players")
data class PlayerEntity(
        @PrimaryKey(autoGenerate = true)
        val id : Int? = null,
        @ColumnInfo(name = "name")
        val name: String?,
)