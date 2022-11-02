package uz.os3ketchup.sputnikgo.presentation.diff

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import uz.os3ketchup.sputnikgo.R
import uz.os3ketchup.sputnikgo.domain.RemainderItem

class RemainderListAdapter :
    ListAdapter<RemainderItem, RemainderItemViewHolder>(RemainderItemDiffCallback()) {

    var onRemainderItemLongClickListener: ((RemainderItem) -> Unit)? = null
    var onRemainderItemClickListener: ((RemainderItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RemainderItemViewHolder {
        val layout = when (viewType) {
            VIEW_TYPE_ENABLED -> R.layout.item_remainder_enabled
            VIEW_TYPE_DISABLED -> R.layout.item_remainder_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return RemainderItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RemainderItemViewHolder, position: Int) {
        val remainderItem = getItem(position)
        holder.name.text = remainderItem.name
        holder.count.text = remainderItem.count.toString()
        holder.view.setOnClickListener {
            onRemainderItemClickListener?.invoke(remainderItem)
        }

        holder.view.setOnLongClickListener {
            onRemainderItemLongClickListener?.invoke(remainderItem)
            true
        }
    }

    override fun getItemViewType(position: Int): Int {

        val item = getItem(position)

        return if (item.enabled) {
            VIEW_TYPE_ENABLED
        } else {
            VIEW_TYPE_DISABLED
        }

    }

    companion object {
        const val VIEW_TYPE_ENABLED = 100
        const val VIEW_TYPE_DISABLED = 101
        const val MAX_POOL_SIZE = 30
    }


}