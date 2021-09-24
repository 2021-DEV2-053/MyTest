package mytest.tictactoe.ui.ingame

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mytest.tictactoe.data.repository.GamesRepositoryImpl
import mytest.tictactoe.domain.repository.GamesRepository
import mytest.tictactoe.util.FakeGamesLocalDataSource
import mytest.tictactoe.util.MainCoroutineRule
import org.junit.Before
import org.junit.Rule
import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import mytest.tictactoe.util.TestData
import org.junit.Test


/**
 * Unit tests for [InGameViewModel]
 */
@ExperimentalCoroutinesApi
class InGameViewModelTest {
    // Subject under test
    private lateinit var inGameViewModel: InGameViewModel
    // Use repository to be injected into the viewmodel
    private lateinit var gamesRepository: GamesRepository

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        val handle = SavedStateHandle()
        handle.set("gameId", TestData.game.gameId);
        handle.set("playerx", TestData.playerX.name);
        handle.set("playero", TestData.playerO.name);
        gamesRepository = GamesRepositoryImpl(FakeGamesLocalDataSource())
        inGameViewModel = InGameViewModel(handle, gamesRepository)
    }

    @Test
    fun verifyIfTheCurrentPlayerIsThePlayerX()  {
        val currentPlayer = inGameViewModel.currentPlayer
        // Check that data were loaded correctly
        assertThat(currentPlayer).isEqualTo(TestData.playerX.name)
    }


    @Test
    fun verifyIfTheFirstMoveIsPlayedCorrectly()  {
        inGameViewModel.onCellClicked(0,0)

        val cell = inGameViewModel.cell.value
        val isCellEmpty = inGameViewModel.isCellEmpty(0, 0)

        assertThat(cell!!.value).isEqualTo('X')
        assertThat(cell.x).isEqualTo(0)
        assertThat(cell.y).isEqualTo(0)

        assertThat(isCellEmpty).isFalse()
    }

    @Test
    fun verifyThatWeCanNotPlayAtTheSameCell()  {
        inGameViewModel.onCellClicked(0,0)
        val counterOfMove = inGameViewModel.counterOfMove
        val currentPlayer = inGameViewModel.currentPlayer
        inGameViewModel.onCellClicked(0,0)
        val NewCounterOfMove = inGameViewModel.counterOfMove
        val NewCurrentPlayer = inGameViewModel.currentPlayer

        assertThat(counterOfMove).isEqualTo(NewCounterOfMove)
        assertThat(currentPlayer).isEqualTo(NewCurrentPlayer)
    }

    @Test
    fun verifyIfTheNextPlayerIsCorrect()  {
        val currentPlayer = inGameViewModel.currentPlayer
        inGameViewModel.onCellClicked(0,0)
        val nextPlayer = inGameViewModel.currentPlayer

        assertThat(currentPlayer).isEqualTo(TestData.playerX.name)
        assertThat(nextPlayer).isEqualTo(TestData.playerO.name)
    }

    @Test
    fun verifyIfWeFinishAfter9Moves()  {

        inGameViewModel.onCellClicked(0,0)
        inGameViewModel.onCellClicked(0,1)
        inGameViewModel.onCellClicked(0,2)
        inGameViewModel.onCellClicked(1,0)
        inGameViewModel.onCellClicked(1,1)
        inGameViewModel.onCellClicked(1,2)
        inGameViewModel.onCellClicked(2,0)
        inGameViewModel.onCellClicked(2,1)
        inGameViewModel.onCellClicked(2,2)

        val counterOfMove = inGameViewModel.counterOfMove
        val isFinish = inGameViewModel.isFinish()

        assertThat(counterOfMove).isEqualTo(9)
        assertThat(isFinish).isTrue()
    }

    @Test
    fun verifyIfWeHaveAWinner()  {
        inGameViewModel.cells = Array(3) {
            arrayOf('.', '.', 'O').toCharArray()
            arrayOf('X', 'O', '.').toCharArray()
            arrayOf('X', '.', '.').toCharArray()
        }

        // play the last move
        inGameViewModel.onCellClicked(0,0)

        val isWin = inGameViewModel.isWin()
        val theWinner = inGameViewModel.theWinner

        assertThat(isWin).isTrue()
        assertThat(theWinner).isNotEmpty()
    }

    @Test
    fun verifyIfWeHaveTheGoodWinner()  {
        val playerX = TestData.playerX.name

        inGameViewModel.cells = Array(3) {
            arrayOf('.', 'X', 'O').toCharArray()
            arrayOf('X', '.', 'O').toCharArray()
            arrayOf('X', '.', '.').toCharArray()
        }

        // play the last move
        inGameViewModel.onCellClicked(0,0)

        val isWin = inGameViewModel.isWin()
        val theWinner = inGameViewModel.theWinner

        assertThat(isWin).isTrue()
        assertThat(theWinner).isEqualTo(playerX)
    }



}