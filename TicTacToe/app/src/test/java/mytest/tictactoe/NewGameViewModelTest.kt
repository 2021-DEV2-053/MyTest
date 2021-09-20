package mytest.tictactoe

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mytest.tictactoe.data.repository.PlayersRepositoryImpl
import mytest.tictactoe.data.source.PlayersLocalDataSource
import mytest.tictactoe.data.source.PlayersLocalDataSourceImpl
import mytest.tictactoe.data.source.db.PlayersDao
import mytest.tictactoe.data.source.mapper.PlayerMapper
import mytest.tictactoe.domain.repository.PlayersRepository
import mytest.tictactoe.result.Result
import mytest.tictactoe.ui.newgame.NewGameViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`


/**
 * Unit tests for [NewGameViewModel]
 */
@ExperimentalCoroutinesApi
class NewGameViewModelTest {

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val testDispatcher = coroutineRule.testDispatcher
    private val coroutineScope = coroutineRule.CoroutineScope()

    private lateinit var playersDao: PlayersDao
    private lateinit var playersLocalDataSource: PlayersLocalDataSource
    private lateinit var playersRepository: PlayersRepository

    @Before
    fun setup() {
        playersDao = mock(PlayersDao::class.java)
        playersLocalDataSource = PlayersLocalDataSourceImpl(playersDao, PlayerMapper())
        playersRepository = PlayersRepositoryImpl(playersLocalDataSource)

        /*val repositoryMock = mock(PlayersRepository::class.java)
        `when`(repositoryMock.getPlayers()).thenReturn(Result.Success(emptyList()))*/
    }

    @Test
    fun shouldReturnEmptyList() = coroutineRule.runBlockingTest {
        // Create ViewModel with the repository
        val viewModel = NewGameViewModel(playersRepository)
        // Observe viewmodel to load players
        val playersData = viewModel.players.value
        // Check that data were loaded correctly
        assertEquals(0, playersData.size)
    }

    @Test
    fun startButtonClickedTest() = coroutineRule.runBlockingTest {
        val viewModel = NewGameViewModel(playersRepository)

        viewModel.onStartClicked("","")
        assertEquals(false, viewModel.isValid.value)

        viewModel.onStartClicked("test","")
        assertEquals(false, viewModel.isValid.value)

        viewModel.onStartClicked("","test")
        assertEquals(false, viewModel.isValid.value)

        viewModel.onStartClicked("test1","test2")
        assertEquals(true, viewModel.isValid.value)
    }

}