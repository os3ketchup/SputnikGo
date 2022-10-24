package uz.os3ketchup.sputnikgo.domain

class GetRemainderItemUseCase(private val remainderListRepository: RemainderListRepository) {
    fun deleteRemainderItem(remainderItem: Int):RemainderItem {
       return remainderListRepository.getRemainderItem(remainderItem)
    }

}