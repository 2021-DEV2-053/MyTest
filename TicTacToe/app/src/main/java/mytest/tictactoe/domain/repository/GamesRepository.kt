package mytest.tictactoe.domain.repository

import mytest.tictactoe.domain.model.Game
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
     * Insert new [Game]s.
     */
    suspend fun startNewGame(playerX: String, playerO: String): Result<Long>
}