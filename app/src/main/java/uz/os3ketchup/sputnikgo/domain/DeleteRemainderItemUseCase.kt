package uz.os3ketchup.sputnikgo.domain

class DeleteRemainderItemUseCase(private val remainderListRepository: RemainderListRepository) {
    fun deleteRemainderItem(remainderItem: RemainderItem) {
        remainderListRepository.deleteRemainderItem(remainderItem)
    }

}