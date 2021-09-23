package mytest.tictactoe.ui.ingame

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mytest.tictactoe.domain.repository.GamesRepository
import javax.inject.Inject

@HiltViewModel
class InGameViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val gamesRepository: GamesRepository
) : ViewModel(){

    val gameId: Long? = savedStateHandle.get("gameId")

    init{

        Log.d("TAG", "gameId: $gameId")
    }

    fun onCellClicked(x: Int, y: Int){


    }

}
