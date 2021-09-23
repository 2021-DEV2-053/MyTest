package mytest.tictactoe.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mytest.tictactoe.util.TestData
import kotlinx.coroutines.test.runBlockingTest
import mytest.tictactoe.data.repository.GamesRepositoryImpl
import mytest.tictactoe.domain.repository.GamesRepository
import mytest.tictactoe.result.ErrorType
import mytest.tictactoe.result.Result.Success
import mytest.tictactoe.result.Result.Error
import mytest.tictactoe.result.data
import mytest.tictactoe.result.succeeded
import mytest.tictactoe.util.FakeGamesLocalDataSource
import org.junit.*
import org.junit.runner.RunWith

/**
 * Unit tests for [GamesRepository]
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class GamesRepositoryTest {
    // Subject under test
    private lateinit var gamesRepository: GamesRepository

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        gamesRepository = GamesRepositoryImpl(FakeGamesLocalDataSource())
    }

    @Test
    fun getGamesTest() = runBlockingTest {
        val result = gamesRepository.getGames()
        assertThat(result.succeeded).isTrue()
        assertThat(result.data!!.size).isEqualTo(TestData.games.size)
    }

}