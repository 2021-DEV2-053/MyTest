package mytest.tictactoe.ui.util

import mytest.tictactoe.R
import mytest.tictactoe.result.ErrorType

class ErrorToastUtils {
    companion object {
        fun fromError(err: ErrorType): Int {
            return when (err){
                ErrorType.ERROR -> R.string.toast_err
                ErrorType.ERROR_PLAYER_X_EMPTY -> R.string.toast_err_player_x_empty
                ErrorType.ERROR_PLAYER_O_EMPTY -> R.string.toast_err_player_o_empty
                ErrorType.ERROR_PLAYERS_NAME_EMPTY -> R.string.toast_err_players_name_empty
                ErrorType.ERROR_PLAYERS_CONFLICT_NAME -> R.string.toast_err_conflict_name
                ErrorType.NO_RESULTS_FOUND -> R.string.toast_err_no_players_found
            }
        }
    }
}