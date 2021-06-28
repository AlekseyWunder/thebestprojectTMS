package com.example.egida

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.egida.R
import com.example.egida.databinding.MainActivityBinding
import com.example.egida.databinding.RowNavDrawerBinding
import com.example.egida.presentation.`object`.NavigationItemModel
import kotlinx.android.synthetic.main.row_nav_drawer.view.*


class NavigationRVAdapter(private var items: ArrayList<NavigationItemModel>, private var currentPos: Int) :RecyclerView.Adapter<NavigationRVAdapter.NavigationItemViewHolder>() {

    private lateinit var context: Context

    class NavigationItemViewHolder(val binding: RowNavDrawerBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationItemViewHolder {
        context = parent.context
        val binding = RowNavDrawerBinding.inflate(LayoutInflater.from(parent.context))
        return NavigationItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: NavigationItemViewHolder, position: Int) {
        // To highlight the selected Item, show different background color
        if (position == currentPos) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
        }
        holder.itemView.navigation_icon
            .setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
        holder.itemView.navigation_title.setTextColor(Color.WHITE)
        //val font = ResourcesCompat.getFont(context, R.font.mycustomfont)
        //holder.itemView.navigation_text.typeface = font
        //holder.itemView.navigation_text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.toFloat())

        holder.itemView.navigation_title.text = items[position].title

        holder.itemView.navigation_icon.setImageResource(items[position].icon)

    }
}