package mytest.tictactoe.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mytest.tictactoe.CoroutineScope
import mytest.tictactoe.MainCoroutineRule
import mytest.tictactoe.data.source.PlayersLocalDataSource
import mytest.tictactoe.data.source.PlayersLocalDataSourceImpl
import mytest.tictactoe.data.source.mapper.PlayerMapper
import mytest.tictactoe.result.Result.Success
import mytest.tictactoe.result.data
import mytest.tictactoe.result.succeeded
import mytest.tictactoe.runBlockingTest
import mytest.tictactoe.ui.newgame.NewGameViewModel
import mytest.tictactoe.util.FakeAppDatabase
import mytest.tictactoe.util.TestData
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Unit tests for [NewGameViewModel]
 */
@ExperimentalCoroutinesApi
class PlayersLocalDataSourceTest {
    // Subject under test
    private lateinit var playersLocalDataSource: PlayersLocalDataSource

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val testDispatcher = coroutineRule.testDispatcher
    private val coroutineScope = coroutineRule.CoroutineScope()


    @Before
    fun setup() {
        playersLocalDataSource = PlayersLocalDataSourceImpl(FakeAppDatabase().playersDao(), PlayerMapper() )
    }

    @Test
    fun insertAndGetPlayer1Test() = coroutineRule.runBlockingTest {
        val result = playersLocalDataSource.insertPlayers(TestData.player1)
        assertThat(result, equalTo(Success(true)))

        val player = playersLocalDataSource.getPlayerByName(TestData.player1.name!!)

        assertThat(player.succeeded, equalTo(true))
        assertThat(player.data!!.name, equalTo(TestData.player1.name))
    }

    @Test
    fun insertTwoPlayersTest() = coroutineRule.runBlockingTest {
        val result = playersLocalDataSource.insertPlayers(TestData.player1, TestData.player2)
        assertThat(result, equalTo(Success(true)))

        val player1 = playersLocalDataSource.getPlayerByName(TestData.player1.name!!)
        val player2 = playersLocalDataSource.getPlayerByName(TestData.player2.name!!)

        assertThat(player1.data!!.name, equalTo(TestData.player1.name))
        assertThat(player2.data!!.name, equalTo(TestData.player2.name))

        val listOfPlayer = playersLocalDataSource.getPlayersByNames(
            TestData.player1.name!!,
            TestData.player2.name!!
        )

        assertThat(listOfPlayer.succeeded, equalTo(true))
        assertThat(listOfPlayer.data!!.size, equalTo(2))
    }

    @Test
    fun insertTwoPlayersWithSameNameTest() = coroutineRule.runBlockingTest {
        val result = playersLocalDataSource.insertPlayers(TestData.player1, TestData.player1)
        assertThat(result, equalTo(Success(true)))

        val resultPlayers = playersLocalDataSource.getPlayers()

        assertThat(resultPlayers.succeeded, equalTo(true))
        assertThat(resultPlayers.data!!.size, equalTo(1))
    }

}