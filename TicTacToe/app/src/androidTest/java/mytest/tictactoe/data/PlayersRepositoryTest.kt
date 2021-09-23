package mytest.tictactoe.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mytest.tictactoe.util.TestData
import kotlinx.coroutines.test.runBlockingTest
import mytest.tictactoe.data.repository.PlayersRepositoryImpl
import mytest.tictactoe.domain.repository.PlayersRepository
import mytest.tictactoe.result.ErrorType
import mytest.tictactoe.result.Result.Success
import mytest.tictactoe.result.Result.Error
import mytest.tictactoe.result.data
import mytest.tictactoe.result.succeeded
import mytest.tictactoe.util.FakePlayersLocalDataSource
import org.junit.*
import org.junit.runner.RunWith

/**
 * Unit tests for [PlayersRepository]
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class PlayersRepositoryTest {
    // Subject under test
    private lateinit var playersRepository: PlayersRepository

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        playersRepository = PlayersRepositoryImpl(FakePlayersLocalDataSource())
    }

    @Test
    fun getPlayersTest() = runBlockingTest {
        val result = playersRepository.getPlayers()
        assertThat(result).isEqualTo(Success(TestData.players))

        assertThat(result.succeeded).isTrue()
        assertThat(result.data!!.size).isEqualTo(TestData.players.size)
    }

    @Test
    fun insertPlayersWithEmptyNameTest() = runBlockingTest {
        val result = playersRepository.insertPlayers("", "")
        assertThat(result.succeeded).isFalse()
        assertThat(result).isInstanceOf(Error::class.java)
        assertThat((result as Error).errorType).isEqualTo(ErrorType.ERROR_PLAYERS_NAME_EMPTY)
    }

    @Test
    fun insertPlayersWithPlayerONameEmptyTest() = runBlockingTest {
        val result = playersRepository.insertPlayers(TestData.player1.name!!, "")
        assertThat(result.succeeded).isFalse()
        assertThat(result).isInstanceOf(Error::class.java)
        assertThat((result as Error).errorType).isEqualTo(ErrorType.ERROR_PLAYER_O_EMPTY)
    }

    @Test
    fun insertPlayersWithPlayerXNameEmptyTest() = runBlockingTest {
        val result = playersRepository.insertPlayers("", TestData.player1.name!!)
        assertThat(result.succeeded).isFalse()
        assertThat(result).isInstanceOf(Error::class.java)
        assertThat((result as Error).errorType).isEqualTo(ErrorType.ERROR_PLAYER_X_EMPTY)
    }

    @Test
    fun insertPlayersWithSameNameTest() = runBlockingTest {
        val result = playersRepository.insertPlayers(TestData.player1.name!!, TestData.player1.name!!)
        assertThat(result.succeeded).isFalse()
        assertThat(result).isInstanceOf(Error::class.java)
        assertThat((result as Error).errorType).isEqualTo(ErrorType.ERROR_PLAYERS_CONFLICT_NAME)
    }


}