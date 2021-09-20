package mytest.tictactoe.ui.newgame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mytest.tictactoe.domain.model.Player
import mytest.tictactoe.domain.repository.PlayersRepository
import mytest.tictactoe.result.data
import mytest.tictactoe.result.succeeded
import javax.inject.Inject

@HiltViewModel
class NewGameViewModel @Inject constructor(
    private val playersRepository: PlayersRepository
) : ViewModel(){

    private val _players = MutableStateFlow<List<Player>>(emptyList())
    private val players : StateFlow<List<Player>> = _players

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            val result = playersRepository.getPlayers()
            if(result.succeeded){
                _players.value = result.data!!
            }
        }
    }

}
