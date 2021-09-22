package mytest.tictactoe.ui.newgame

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import mytest.tictactoe.R
import mytest.tictactoe.domain.model.Player

class PlayersAdapter(
    val ctx: Context,
    val players: List<Player>
): ArrayAdapter<Player>(ctx, R.layout.item_player, players) {

    val layoutInflater = ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val player: Player? = getItem(position)
        var view: View? = convertView
        if(convertView == null){
            view = layoutInflater.inflate(R.layout.item_player, null) as View
        }
        view!!.findViewById<TextView>(R.id.name_textView).text = player!!.name

        return view
    }


}