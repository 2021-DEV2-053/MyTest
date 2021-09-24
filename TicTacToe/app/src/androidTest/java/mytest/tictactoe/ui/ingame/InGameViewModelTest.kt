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

    /**
     * check if we start well with the playerX, that's mean we get data from Bundle(navigation)
     * X | . | .
     * . | . | .
     * . | . | .
     * */
    @Test
    fun verifyIfTheCurrentPlayerIsThePlayerX()  {
        val currentPlayer = inGameViewModel.currentPlayer
        // Check that data were loaded correctly
        assertThat(currentPlayer).isEqualTo(TestData.playerX.name)
    }

    /**
     * check if the first move works ( 0, 0 ), that's means that we can play
     * X | . | .
     * . | . | .
     * . | . | .
     * */
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
    /**
     * check if the second Player (O) play at the same position that already played ( O,O )
     * X | . | .
     * . | . | .
     * . | . | .
     * */
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
    /**
     * check if the CurrentPlayer is changed after playing a move  ( O,O )
     * */
    @Test
    fun verifyIfTheNextPlayerIsCorrect()  {
        val currentPlayer = inGameViewModel.currentPlayer
        inGameViewModel.onCellClicked(0,0)
        val nextPlayer = inGameViewModel.currentPlayer

        assertThat(currentPlayer).isEqualTo(TestData.playerX.name)
        assertThat(nextPlayer).isEqualTo(TestData.playerO.name)
    }
    /**
     * check if the game is finish after all cells are completed and that we have no winner.
     * X | X | O
     * O | X | X
     * X | O | O
     * */
    @Test
    fun verifyIfWeFinishAfter9Moves()  {
        inGameViewModel.onCellClicked(0,0)
        inGameViewModel.onCellClicked(2,2)
        inGameViewModel.onCellClicked(1,1)
        inGameViewModel.onCellClicked(2,0)
        inGameViewModel.onCellClicked(1,0)
        inGameViewModel.onCellClicked(0,1)
        inGameViewModel.onCellClicked(2,1)
        inGameViewModel.onCellClicked(1,2)
        inGameViewModel.onCellClicked(0,2)

        val counterOfMove = inGameViewModel.counterOfMove
        val isFinish = inGameViewModel.isFinish()

        assertThat(counterOfMove).isEqualTo(9)
        assertThat(isFinish).isTrue()
    }
    /**
     * check if we have a winner.
     * . | . | O
     * X | O | .
     * X | . | .
     * */
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
    /**
     * check if we have the good winner Player(X), when he play the last move ( O,O )
     * . | X | O
     * X | . | O
     * X | . | .
     * */
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
    /**
     * check if we have a winner after a move (2, 0) to get a diagonal sequence
     * . | X | .
     * O | X | O
     * X | . | .
     * */
    @Test
    fun verifyIfDiagonalVictoryWork()  {
        val playerX = TestData.playerX.name

        inGameViewModel.cells = Array(3) {
            arrayOf('.', 'X', '.').toCharArray()
            arrayOf('O', 'X', 'O').toCharArray()
            arrayOf('X', '.', '.').toCharArray()
        }

        // play the last move
        inGameViewModel.onCellClicked(2,0)

        val isWin = inGameViewModel.isWin()
        val theWinner = inGameViewModel.theWinner

        assertThat(isWin).isTrue()
        assertThat(theWinner).isEqualTo(playerX)
    }


}