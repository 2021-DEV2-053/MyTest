package mytest.tictactoe.ui.newgame

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mytest.tictactoe.domain.model.Player
import mytest.tictactoe.domain.repository.PlayersRepository
import mytest.tictactoe.result.ErrorType
import mytest.tictactoe.result.Result
import mytest.tictactoe.result.data
import mytest.tictactoe.result.succeeded
import javax.inject.Inject

@HiltViewModel
class NewGameViewModel @Inject constructor(
    private val playersRepository: PlayersRepository
) : ViewModel(){

    private val _players = MutableStateFlow<List<Player>>(emptyList())
    val players : StateFlow<List<Player>> = _players

    private val _error = MutableStateFlow<ErrorType?>(null)
    val error : StateFlow<ErrorType?> = _error

    private val _isStarting = MutableStateFlow(false)
    val isStarting : StateFlow<Boolean> = _isStarting

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            val result = playersRepository.getPlayers()
            when(result){
                is Result.Success -> {
                    _players.value = result.data
                }
                is Result.Error -> {
                    _error.value = result.errorType
                }
            }
        }
    }


    private fun startNewGame(playerX: String, playerO: String) {
        viewModelScope.launch {
            val result =  playersRepository.insertPlayers(playerX, playerO)
            when(result){
                is Result.Success -> {
                    _isStarting.value = true
                }
                is Result.Error -> {
                    _error.value = result.errorType
                }
            }
        }
    }

    fun onStartClicked(playerX: String, playerO: String){
        startNewGame(playerX, playerO)

    }

    fun onStarted(){
        _isStarting.value = false
    }
    fun onErrorShowed(){
        _error.value = null
    }

}
