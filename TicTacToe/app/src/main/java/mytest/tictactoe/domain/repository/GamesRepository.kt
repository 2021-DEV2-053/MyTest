package mytest.tictactoe.domain.repository

import mytest.tictactoe.domain.model.Game
import mytest.tictactoe.domain.model.Player
import mytest.tictactoe.result.Result

/**
 *
 * Interface to the data layer
 */
interface GamesRepository {
    /**
     *
     * Return a list of [Game]s.
     */
    suspend fun getGames(): Result<List<Game>>
    /**
     *
     * start a new [Game].
     */
    suspend fun startNewGame(playerX: Player, playerO: Player): Result<Long>
    /**
     *
     * Return the new [Game].
     */
    suspend fun getTheNewGame(gameId: Long): Result<Game>

    /**
     *
     * Set The EndedDate.
     */
    suspend fun endGame(game: Game)

}