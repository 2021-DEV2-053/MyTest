package mytest.tictactoe.data.source.entity

import androidx.room.*
import java.util.*

@Entity(tableName = "games")
data class GameEntity(
        @PrimaryKey(autoGenerate = true)
        val id : Int? = null,
        @ColumnInfo(name = "createdAt")
        val createdAt: Date?,
        @ColumnInfo(name = "endedAt")
        val endedAt: Date?,
        @ColumnInfo(name = "status")
        val status: String?,
        @ColumnInfo(name = "player_x")
        @Embedded val playerx: PlayerEntity?,
        @ColumnInfo(name = "player_o")
        @Embedded val playero: PlayerEntity?,
        @ColumnInfo(name = "current_player")
        @Embedded val currentPlayer: PlayerEntity?,
        @ColumnInfo(name = "winning_player")
        @Embedded val winningPlayer: PlayerEntity?,
        @ColumnInfo(name = "losing_player")
        @Embedded val losingPlayer: PlayerEntity?
)