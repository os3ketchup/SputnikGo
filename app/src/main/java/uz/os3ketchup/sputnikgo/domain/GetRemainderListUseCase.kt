package uz.os3ketchup.sputnikgo.domain

import androidx.lifecycle.LiveData

class GetRemainderListUseCase(private val remainderListRepository: RemainderListRepository) {
    fun getRemainderList():LiveData< List<RemainderItem>> {
        return remainderListRepository.getRemainderList()

    }
}