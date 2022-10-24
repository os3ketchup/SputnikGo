package uz.os3ketchup.sputnikgo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.os3ketchup.sputnikgo.domain.RemainderItem
import uz.os3ketchup.sputnikgo.domain.RemainderListRepository
import kotlin.random.Random

object RemainderListRepositoryImpl : RemainderListRepository {

    private val remainderListLD = MutableLiveData<List<RemainderItem>>()
    private val remainderList = sortedSetOf<RemainderItem>(
        { v1, v2 -> v1.id.compareTo(v2.id) }
    )
    private var autoIncrement = 0

    init {
        // TODO("get Firebase realtime databases")
    }

    override fun addRemainderItem(remainderItem: RemainderItem) {
        if (remainderItem.id == RemainderItem.UNDEFINED_ID) {
            remainderItem.id = autoIncrement++
        }
    }

    override fun deleteRemainderItem(remainderItem: RemainderItem) {
        remainderList.remove(remainderItem)
    }

    override fun editRemainderItem(remainderItem: RemainderItem) {
        val oldElement = getRemainderItem(remainderItem.id)
        remainderList.remove(oldElement)
        addRemainderItem(remainderItem)
    }

    override fun getRemainderItem(remainderItemId: Int): RemainderItem {
        return remainderList.find {
            it.id == remainderItemId
        } ?: throw RuntimeException("Element with id $remainderItemId not found")
    }

    override fun getRemainderList(): LiveData<List<RemainderItem>> {
        return remainderListLD
    }

}
