package mytest.tictactoe.ui.ingame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import mytest.tictactoe.R
import mytest.tictactoe.databinding.FragmentInGameBinding
import mytest.tictactoe.domain.model.Cell
import mytest.tictactoe.ui.util.launchAndRepeatWithViewLifecycle

@AndroidEntryPoint
class InGameFragment : Fragment(R.layout.fragment_in_game) {

    private lateinit var binding: FragmentInGameBinding
    private val viewModel: InGameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentInGameBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        observeViewModel()
    }

    private fun observeViewModel() {
        launchAndRepeatWithViewLifecycle{
            viewModel.messageToPlayer.collect { message ->
                if(message != null){
                   binding.messageTextView.text = message
                }
            }
        }
        launchAndRepeatWithViewLifecycle{
            viewModel.cell.collect { cell ->
                if(cell != null){
                    getTextView(cell).text = cell.value.toString()
                }
            }
        }
    }
    private fun getTextView(cell: Cell): TextView {
        return when (cell.toString()){
            "00" -> binding.textView00
            "01" -> binding.textView01
            "02" -> binding.textView02
            "10" -> binding.textView10
            "11" -> binding.textView11
            "12" -> binding.textView12
            "20" -> binding.textView20
            "21" -> binding.textView21
            "22" -> binding.textView22
            else -> binding.messageTextView
        }
    }
}