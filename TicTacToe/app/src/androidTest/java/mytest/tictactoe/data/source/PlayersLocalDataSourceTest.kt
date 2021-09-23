package mytest.tictactoe.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mytest.tictactoe.data.source.mapper.PlayerMapper
import mytest.tictactoe.util.TestData
import kotlinx.coroutines.test.runBlockingTest
import mytest.tictactoe.data.source.db.AppDatabase
import mytest.tictactoe.data.source.db.PlayersDao
import mytest.tictactoe.result.Result.Success
import mytest.tictactoe.result.data
import mytest.tictactoe.result.succeeded
import org.junit.*
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Unit tests for [PlayersLocalDataSource]
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class PlayersLocalDataSourceTest {
    // Subject under test
    private lateinit var playersLocalDataSource: PlayersLocalDataSource

    private lateinit var playersDao: PlayersDao
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

        playersDao = db.playersDao()
        playersLocalDataSource = PlayersLocalDataSourceImpl(playersDao, PlayerMapper())
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndGetPlayer1Test() = runBlockingTest {
        val result = playersLocalDataSource.insertPlayers(TestData.player1)
        assertThat(result).isEqualTo(Success(true))

        val playerDomain = playersLocalDataSource.getPlayerByName(TestData.player1.name!!)

        assertThat(playerDomain.succeeded).isTrue()
        assertThat(playerDomain.data!!.name).isEqualTo(TestData.player1.name)
    }

    @Test
    fun insertTwoPlayersTest() = runBlockingTest {
        val result = playersLocalDataSource.insertPlayers(TestData.player1, TestData.player2)
        assertThat(result).isEqualTo(Success(true))

        val listOfPlayer = playersLocalDataSource.getPlayersByNames(
            TestData.player1.name!!,
            TestData.player2.name!!
        )

        assertThat(listOfPlayer.succeeded).isTrue()
        assertThat(listOfPlayer.data!!.size).isEqualTo(2)

    }

    @Test
    fun insertTwoPlayersWithSameNameTest() = runBlockingTest {
        val result = playersLocalDataSource.insertPlayers(TestData.player1, TestData.player1)
        assertThat(result).isEqualTo(Success(true))

        val resultPlayers = playersLocalDataSource.getPlayers()

        assertThat(resultPlayers.succeeded).isTrue()
        assertThat(resultPlayers.data!!.size).isEqualTo(1)
    }

}