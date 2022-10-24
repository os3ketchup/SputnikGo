package uz.os3ketchup.sputnikgo.domain

interface RemainderListRepository {
    fun addRemainderItem(remainderItem: RemainderItem)
    fun deleteRemainderItem(remainderItem: RemainderItem)
    fun editRemainderItem(remainderItem: RemainderItem)
    fun getRemainderItem(remainderItem: Int): RemainderItem
    fun getRemainderList(): List<RemainderItem>

}