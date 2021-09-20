package mytest.tictactoe.ui.newgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import mytest.tictactoe.R
import mytest.tictactoe.databinding.FragmentNewGameBinding
import mytest.tictactoe.ui.util.launchAndRepeatWithViewLifecycle

/**
 * A simple [Fragment] subclass.
 * Use the [NewGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class NewGameFragment : Fragment(R.layout.fragment_new_game) {

    private lateinit var binding: FragmentNewGameBinding
    private val viewModel: NewGameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewGameBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        launchAndRepeatWithViewLifecycle{
            viewModel.players.collect { players ->
                val adapter = PlayersAdapter(
                    requireContext(),
                    players
                )
                binding.playerXAutoCompleteTextView.setAdapter(adapter)
                binding.playerOAutoCompleteTextView.setAdapter(adapter)
            }
        }
    }
}