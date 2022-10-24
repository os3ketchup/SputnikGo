package uz.os3ketchup.sputnikgo.domain

import androidx.lifecycle.LiveData

interface RemainderListRepository {
    fun addRemainderItem(remainderItem: RemainderItem)
    fun deleteRemainderItem(remainderItem: RemainderItem)
    fun editRemainderItem(remainderItem: RemainderItem)
    fun getRemainderItem(remainderItemId: Int): RemainderItem
    fun getRemainderList(): LiveData<List<RemainderItem>>

}