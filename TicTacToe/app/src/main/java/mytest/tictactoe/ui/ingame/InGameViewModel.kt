package mytest.tictactoe.ui.ingame

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mytest.tictactoe.domain.model.Cell
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
    val playerX: String? = savedStateHandle.get("playerx")
    val playerO: String? = savedStateHandle.get("playero")
    var currentPlayer = playerX

    var game: Game? = null
    var cells = Array(3) {CharArray(3)}
    var counterOfMove = 0
    var theWinner: String? = null


    // A player set a move, send to the view which cell is it.
    private val _cell = MutableStateFlow<Cell?>(null)
    val cell : StateFlow<Cell?> = _cell

    // Set which player have to play
    private val _messageToPlayer = MutableStateFlow<String?>(null)
    val messageToPlayer : StateFlow<String?> = _messageToPlayer

    init{
        _messageToPlayer.value = "$currentPlayer is your turn"
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

    private fun endGame() {
        viewModelScope.launch {
            gamesRepository.endGame(game!!)
        }
    }


    //set the cells with a new value and update the UI
    fun makeMove(x: Int, y: Int){
        cells[x][y] = if(currentPlayer == playerX) 'X' else 'O'
        _cell.value = Cell(x, y, cells[x][y])
        counterOfMove++
    }

    //check the cell if is not already play it
    fun isCellEmpty(x: Int, y: Int): Boolean{
        return cells[x][y] != 'X' && cells[x][y] != 'O'
    }

    //switch the players and update the UI
    fun nextPlayer(){
        currentPlayer = if(currentPlayer == playerX) playerO else playerX
        _messageToPlayer.value = "$currentPlayer is your turn"
    }

    //Check is the cells is completed or if we get a winner
    fun isFinish(): Boolean{
        //check if the cells is all completed
        if(counterOfMove == 9){
            _messageToPlayer.value = "No one win !"
            endGame()
            return true
        }

        //check if we have a winner
        if(isWin()){
            _messageToPlayer.value = "$theWinner is the winner !!!"
            endGame()
            return true
        }
        return false
    }

    fun isWin(): Boolean{
        var isWin = false
        //by column
        for (column in cells) {
            if(String(column) == "XXX"){
                theWinner = playerX
                isWin = true
            }
            if(String(column) == "OOO"){
                theWinner = playerO
                isWin = true
            }
        }
        //by row
        var row0 = ""
        var row1 = ""
        var row2 = ""
        for (column in cells) {
            row0 += column[0]
            row1 += column[1]
            row2 += column[2]
        }
        if(row0 == "XXX" || row1 == "XXX" || row2 == "XXX"){
            theWinner = playerX
            isWin = true
        }
        if(row0 == "OOO" || row1 == "OOO" || row2 == "OOO"){
            theWinner = playerO
            isWin = true
        }

        //by diagonal
        var diag0 = ""
        var diag1 = ""
        for ((rowId, column) in cells.withIndex()) {
            diag0 += column[0]
            if(rowId == 1) diag0 += column[1]
            if(rowId == 2) diag0 += column[2]

            diag1 += column[2]
            if(rowId == 1) diag1 += column[1]
            if(rowId == 2) diag1 += column[0]

        }
        if(diag0 == "XXX" || diag1 == "XXX"){
            theWinner = playerX
            isWin = true
        }
        if(diag0 == "OOO" || diag1 == "OOO"){
            theWinner = playerO
            isWin = true
        }

        return isWin
    }


    fun onCellClicked(x: Int, y: Int){
        if(!isFinish() && isCellEmpty(x, y)){
            makeMove(x, y)
            nextPlayer()
            isFinish()
        }
    }

}
