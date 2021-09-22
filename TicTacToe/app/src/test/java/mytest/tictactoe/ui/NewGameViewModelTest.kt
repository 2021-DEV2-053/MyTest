package mytest.tictactoe.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mytest.tictactoe.CoroutineScope
import mytest.tictactoe.MainCoroutineRule
import mytest.tictactoe.data.repository.PlayersRepositoryImpl
import mytest.tictactoe.data.source.PlayersLocalDataSource
import mytest.tictactoe.data.source.PlayersLocalDataSourceImpl
import mytest.tictactoe.data.source.mapper.PlayerMapper
import mytest.tictactoe.domain.repository.PlayersRepository
import mytest.tictactoe.result.ErrorType
import mytest.tictactoe.runBlockingTest
import mytest.tictactoe.ui.newgame.NewGameViewModel
import mytest.tictactoe.util.FakeAppDatabase
import org.junit.Assert.assertEquals
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
    private lateinit var playersLocalDataSource: PlayersLocalDataSource
    private lateinit var playersRepository: PlayersRepository

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()


    @Before
    fun setup() {
        playersLocalDataSource = PlayersLocalDataSourceImpl(FakeAppDatabase().playersDao(), PlayerMapper() )
        playersRepository = PlayersRepositoryImpl(playersLocalDataSource)
        newGameViewModel = NewGameViewModel(playersRepository)
    }

    @Test
    fun playersIsLoaded()  {
        // Observe viewmodel to load players
        val players = newGameViewModel.players.value
        // Check that data were loaded correctly
        assertEquals(0, players.size)
    }

    @Test
    fun verifyErrorResultTest() {

        newGameViewModel.onStartClicked("","")
        assertEquals(ErrorType.ERROR_PLAYERS_NAME_EMPTY, newGameViewModel.error.value)

        newGameViewModel.onStartClicked("test","")
        assertEquals(ErrorType.ERROR_PLAYER_O_EMPTY, newGameViewModel.error.value)

        newGameViewModel.onStartClicked("","test")
        assertEquals(ErrorType.ERROR_PLAYER_X_EMPTY, newGameViewModel.error.value)

        newGameViewModel.onStartClicked("test1","test1")
        assertEquals(ErrorType.ERROR_PLAYERS_CONFLICT_NAME, newGameViewModel.error.value)
    }



}