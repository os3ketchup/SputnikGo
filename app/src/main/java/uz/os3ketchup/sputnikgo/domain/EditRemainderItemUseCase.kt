package uz.os3ketchup.sputnikgo.domain

class EditRemainderItemUseCase(private val remainderListRepository: RemainderListRepository) {
    fun deleteRemainderItem(remainderItem: RemainderItem) {
        remainderListRepository.editRemainderItem(remainderItem)
    }

}