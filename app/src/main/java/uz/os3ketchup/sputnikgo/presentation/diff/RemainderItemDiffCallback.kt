package uz.os3ketchup.sputnikgo.presentation.diff

import androidx.recyclerview.widget.DiffUtil
import uz.os3ketchup.sputnikgo.domain.RemainderItem

class RemainderItemDiffCallback : DiffUtil.ItemCallback<RemainderItem>() {
    override fun areItemsTheSame(oldItem: RemainderItem, newItem: RemainderItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RemainderItem, newItem: RemainderItem): Boolean {
        return oldItem == newItem
    }

}