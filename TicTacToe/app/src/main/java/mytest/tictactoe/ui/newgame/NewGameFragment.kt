package mytest.tictactoe.ui.newgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mytest.tictactoe.R

/**
 * A simple [Fragment] subclass.
 * Use the [NewGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewGameFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_game, container, false)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            NewGameFragment().apply { }
    }
}