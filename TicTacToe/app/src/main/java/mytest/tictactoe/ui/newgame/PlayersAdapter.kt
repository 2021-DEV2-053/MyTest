package mytest.tictactoe.ui.newgame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import mytest.tictactoe.R
import mytest.tictactoe.domain.model.Player
import java.util.*

class PlayersAdapter(
    val ctx: Context,
    val allPlayers: List<Player>
): ArrayAdapter<Player>(ctx, R.layout.item_player, allPlayers) {

    private var players: List<Player> = allPlayers

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                R.layout.item_player, parent, false
            )
        }
        val textViewName = convertView!!.findViewById<TextView>(R.id.name_textView)
        val player: Player? = getItem(position)
        if (player != null) {
            textViewName.text = player.name
        }
        return convertView
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                players = filterResults.values as List<Player>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.lowercase(Locale.getDefault())

                val filterResults = FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    allPlayers
                else
                    allPlayers.filter {
                        it.name!!.lowercase(Locale.getDefault()).contains(queryString)
                    }
                return filterResults
            }
        }
    }

}