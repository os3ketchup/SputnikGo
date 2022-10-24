package uz.os3ketchup.sputnikgo.domain

class AddRemainderItemUseCase(private val remainderListRepository: RemainderListRepository) {
    fun addRemainderItem(remainderItem: RemainderItem) {
        remainderListRepository.addRemainderItem(remainderItem)
    }

}