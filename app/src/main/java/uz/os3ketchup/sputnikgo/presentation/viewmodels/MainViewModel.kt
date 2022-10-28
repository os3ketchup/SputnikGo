package uz.os3ketchup.sputnikgo.presentation.viewmodels

import androidx.lifecycle.ViewModel
import uz.os3ketchup.sputnikgo.data.RemainderListRepositoryImpl
import uz.os3ketchup.sputnikgo.domain.DeleteRemainderItemUseCase
import uz.os3ketchup.sputnikgo.domain.EditRemainderItemUseCase
import uz.os3ketchup.sputnikgo.domain.GetRemainderListUseCase
import uz.os3ketchup.sputnikgo.domain.RemainderItem

class MainViewModel : ViewModel() {
    private val repository = RemainderListRepositoryImpl

    private val getRemainderListUseCase = GetRemainderListUseCase(repository)
    private val deleteRemainderItemUseCase = DeleteRemainderItemUseCase(repository)
    private val editShopItemUseCase = EditRemainderItemUseCase(repository)

    val remainderList = getRemainderListUseCase.getRemainderList()

    fun deleteItem(remainderItem: RemainderItem){
        deleteRemainderItemUseCase.deleteRemainderItem(remainderItem)
    }

    fun changeEnabledState(remainderItem: RemainderItem){
        val newItem = remainderItem.copy(enabled = !remainderItem.enabled)
        editShopItemUseCase.editRemainderItem(newItem)
    }
}