package mytest.tictactoe.data.source.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "games")
data class GameEntity(
        @PrimaryKey(autoGenerate = true)
        val gameId : Long? = null,
        @ColumnInfo(name = "createdAt")
        val createdAt: Date?,
        @ColumnInfo(name = "endedAt")
        val endedAt: Date? = null,
        @ColumnInfo(name = "player_x")
        val playerxId: Long? = null,
        @ColumnInfo(name = "player_o")
        val playeroId: Long? = null,
        @ColumnInfo(name = "winning_player")
        val winningPlayerId: Long? = null
)