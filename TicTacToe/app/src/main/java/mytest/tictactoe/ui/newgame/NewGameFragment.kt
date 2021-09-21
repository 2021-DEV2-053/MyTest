package mytest.tictactoe.ui.newgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import mytest.tictactoe.R
import mytest.tictactoe.databinding.FragmentNewGameBinding
import mytest.tictactoe.ui.util.launchAndRepeatWithViewLifecycle

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
        binding.startButton.setOnClickListener {
            val playerX = binding.playerXAutoCompleteTextView.text.toString()
            val playerO = binding.playerXAutoCompleteTextView.text.toString()
            viewModel.onStartClicked(playerX, playerO)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        launchAndRepeatWithViewLifecycle{
            viewModel.players.collect { players ->
                val adapter = PlayersAdapter(requireContext(), players)
                binding.playerXAutoCompleteTextView.setAdapter(adapter)
                binding.playerOAutoCompleteTextView.setAdapter(adapter)
            }
        }

        launchAndRepeatWithViewLifecycle{
            viewModel.isValid.collect { isValid ->
               if(isValid != null && isValid){
                   findNavController().navigate(R.id.action_newGameFragment_to_inGameFragment)
               }else if(isValid != null && !isValid){
                   Toast.makeText(requireContext(), R.string.toast_err_name, Toast.LENGTH_SHORT).show()
               }
            }
        }
    }
}