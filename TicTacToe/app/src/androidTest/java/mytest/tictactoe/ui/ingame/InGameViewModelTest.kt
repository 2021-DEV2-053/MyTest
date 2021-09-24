package mytest.tictactoe.ui.ingame

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import mytest.tictactoe.util.MainCoroutineRule
import org.junit.Before
import org.junit.Rule


/**
 * Unit tests for [InGameViewModel]
 */
@ExperimentalCoroutinesApi
class InGameViewModelTest {
    // Subject under test
    private lateinit var inGameViewModel: InGameViewModel

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
    }




}