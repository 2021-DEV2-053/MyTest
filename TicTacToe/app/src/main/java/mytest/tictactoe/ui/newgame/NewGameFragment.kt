package mytest.tictactoe.ui.newgame

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import mytest.tictactoe.R
import mytest.tictactoe.databinding.FragmentNewGameBinding
import mytest.tictactoe.domain.model.Player
import mytest.tictactoe.ui.util.ErrorToastUtils.Companion.fromError
import mytest.tictactoe.ui.util.launchAndRepeatWithViewLifecycle

@AndroidEntryPoint
class NewGameFragment : Fragment(R.layout.fragment_new_game) {

    private lateinit var binding: FragmentNewGameBinding
    private val viewModel: NewGameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startButton.setOnClickListener {
            binding.progressCircular.visibility = VISIBLE
            binding.startButton.visibility = GONE
            val playerX = binding.playerXAutoCompleteTextView.text.toString()
            val playerO = binding.playerOAutoCompleteTextView.text.toString()
            viewModel.onStartClicked(playerX, playerO)
        }
        binding.playerXAutoCompleteTextView.setOnItemClickListener() { parent, _, position, id ->
            val player = parent.adapter.getItem(position) as Player?
            binding.playerXAutoCompleteTextView.setText(player!!.name)
        }
        binding.playerOAutoCompleteTextView.setOnItemClickListener() { parent, _, position, id ->
            val player = parent.adapter.getItem(position) as Player?
            binding.playerOAutoCompleteTextView.setText(player!!.name)
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        launchAndRepeatWithViewLifecycle{
            viewModel.players.collect { players ->
                if(players.isNotEmpty()){
                    val adapter = PlayersAdapter(requireContext(), players)
                    binding.playerXAutoCompleteTextView.setAdapter(adapter)
                    binding.playerOAutoCompleteTextView.setAdapter(adapter)
                }
            }
        }

        launchAndRepeatWithViewLifecycle{
            viewModel.error.collect { errorType ->
                if(errorType != null){
                    Toast.makeText(requireContext(), fromError(errorType), Toast.LENGTH_SHORT).show()
                    viewModel.onErrorShowed()
                }
                binding.progressCircular.visibility = GONE
                binding.startButton.visibility = VISIBLE
            }
        }

        launchAndRepeatWithViewLifecycle{
            viewModel.startTheGame.collect { gameID ->
               if(gameID != null){
                   val bundle = bundleOf("gameId" to gameID)
                   findNavController().navigate(R.id.action_newGameFragment_to_inGameFragment)
               }
            }
        }
    }
}