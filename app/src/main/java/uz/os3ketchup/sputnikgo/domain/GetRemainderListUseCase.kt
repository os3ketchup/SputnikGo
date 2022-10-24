package uz.os3ketchup.sputnikgo.domain

class GetRemainderListUseCase(private val remainderListRepository: RemainderListRepository) {
    fun getRemainderList(): List<RemainderItem> {
        return remainderListRepository.getRemainderList()

    }
}