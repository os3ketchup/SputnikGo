package uz.os3ketchup.sputnikgo.presentation.diff

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.os3ketchup.sputnikgo.R

class RemainderItemViewHolder(val view: View):ViewHolder(view) {
    val name:TextView = view.findViewById(R.id.tv_name)
    val count:TextView = view.findViewById(R.id.tv_count)
}