package mytest.tictactoe.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mytest.tictactoe.util.TestData
import kotlinx.coroutines.test.runBlockingTest
import mytest.tictactoe.data.source.db.AppDatabase
import mytest.tictactoe.data.source.db.GamesDao
import mytest.tictactoe.data.source.mapper.GameMapper
import mytest.tictactoe.domain.model.Game
import mytest.tictactoe.result.data
import mytest.tictactoe.result.succeeded
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Unit tests for [GamesLocalDataSource]
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class GamesLocalDataSourceTest {
    // Subject under test
    private lateinit var gamesLocalDataSource: GamesLocalDataSource

    private lateinit var gamesDao: GamesDao
    private lateinit var db: AppDatabase

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun createDb() {
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()

        gamesDao = db.gamesDao()
        gamesLocalDataSource = GamesLocalDataSourceImpl(gamesDao, GameMapper())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
    /**
     *
     * check if the [Game] inserted.
     */
    @Test
    fun insertNewGameTest() = runBlockingTest {
        val result = gamesLocalDataSource.insertGame(TestData.game)
        assertThat(result.succeeded).isTrue()
        assertThat(result.data).isEqualTo(1)
    }
    /**
     *
     * check if we can a specific [Game], we need it to start the game
     */
    @Test
    fun findASpecificGame() = runBlockingTest {
        gamesLocalDataSource.insertGame(TestData.game)
        val result = gamesLocalDataSource.getGame(TestData.game.gameId!!)

        assertThat(result.succeeded).isTrue()
        assertThat(result.data!!.gameId!!).isEqualTo(1)
    }

    @Test
    fun updateASpecificGame() = runBlockingTest {

    }

}