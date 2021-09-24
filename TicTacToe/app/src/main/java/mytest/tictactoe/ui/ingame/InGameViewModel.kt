package mytest.tictactoe.ui.ingame

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mytest.tictactoe.domain.model.Game
import mytest.tictactoe.domain.repository.GamesRepository
import mytest.tictactoe.result.Result
import javax.inject.Inject

@HiltViewModel
class InGameViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val gamesRepository: GamesRepository
) : ViewModel(){

    val gameId: Long? = savedStateHandle.get("gameId")
    var game: Game? = null

    init{
        fetchTheGame()
    }
    private fun fetchTheGame() {
        viewModelScope.launch {
            val result = gamesRepository.getTheNewGame(gameId!!)
            when(result){
                is Result.Success -> {
                    game = result.data
                }
                is Result.Error -> {

                }
            }
        }
    }

    fun onCellClicked(x: Int, y: Int){


    }

}
