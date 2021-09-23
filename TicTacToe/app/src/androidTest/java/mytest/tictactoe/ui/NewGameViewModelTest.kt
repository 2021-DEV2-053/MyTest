package mytest.tictactoe.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mytest.tictactoe.data.repository.PlayersRepositoryImpl
import mytest.tictactoe.domain.repository.PlayersRepository
import mytest.tictactoe.result.ErrorType
import mytest.tictactoe.ui.newgame.NewGameViewModel
import mytest.tictactoe.util.FakePlayersLocalDataSource
import mytest.tictactoe.util.MainCoroutineRule
import mytest.tictactoe.util.TestData
import org.junit.Before
import org.junit.Rule
import org.junit.Test


/**
 * Unit tests for [NewGameViewModel]
 */
@ExperimentalCoroutinesApi
class NewGameViewModelTest {
    // Subject under test
    private lateinit var newGameViewModel: NewGameViewModel

    // Use repository to be injected into the viewmodel
    private lateinit var playersRepository: PlayersRepository

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        playersRepository = PlayersRepositoryImpl(FakePlayersLocalDataSource())
        newGameViewModel = NewGameViewModel(playersRepository)
    }

    @Test
    fun playersIsLoaded()  {
        // Observe viewmodel to load players
        val players = newGameViewModel.players.value
        // Check that data were loaded correctly
        assertThat(players.size).isEqualTo(TestData.players.size)
    }

    @Test
    fun verifyErrorResultTest() {
        newGameViewModel.onStartClicked("","")
        assertThat(newGameViewModel.error.value).isEqualTo(ErrorType.ERROR_PLAYERS_NAME_EMPTY)

        newGameViewModel.onStartClicked("test","")
        assertThat(newGameViewModel.error.value).isEqualTo(ErrorType.ERROR_PLAYER_O_EMPTY)

        newGameViewModel.onStartClicked("","test")
        assertThat(newGameViewModel.error.value).isEqualTo(ErrorType.ERROR_PLAYER_X_EMPTY)

        newGameViewModel.onStartClicked("test1","test1")
        assertThat(newGameViewModel.error.value).isEqualTo(ErrorType.ERROR_PLAYERS_CONFLICT_NAME)

    }



}