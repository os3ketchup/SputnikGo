package uz.os3ketchup.sputnikgo.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.os3ketchup.sputnikgo.data.RemainderListRepositoryImpl
import uz.os3ketchup.sputnikgo.domain.AddRemainderItemUseCase
import uz.os3ketchup.sputnikgo.domain.EditRemainderItemUseCase
import uz.os3ketchup.sputnikgo.domain.GetRemainderItemUseCase
import uz.os3ketchup.sputnikgo.domain.RemainderItem

class RemainderItemViewModel : ViewModel() {

    private val repository = RemainderListRepositoryImpl

    private val getRemainderItemUseCase = GetRemainderItemUseCase(repository)
    private val addRemainderItemUseCase = AddRemainderItemUseCase(repository)
    private val editRemainderItemUseCase = EditRemainderItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorName: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _remainderItem = MutableLiveData<RemainderItem>()
    val remainderItem: LiveData<RemainderItem>
        get() = _remainderItem

    fun getRemainderItem(remainderItemId: Int) {
        val item = getRemainderItemUseCase.getRemainderItem(remainderItemId)
        _remainderItem.value = item
    }

    private val _shouldCloseScreen = MutableLiveData<Unit>()
    val shouldCloseScreen: LiveData<Unit>
        get() = _shouldCloseScreen

    fun addRemainderItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            val remainderItem = RemainderItem(name = name, count = count, true)
            addRemainderItemUseCase.addRemainderItem(remainderItem)
            finishWork()
        }
    }
    fun editRemainderItem(inputName: String?, inputCount: String?) {
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if (fieldsValid) {
            _remainderItem.value?.let {
                val item =it.copy(name = name, count = count)
                editRemainderItemUseCase.editRemainderItem(item)
                finishWork()
            }
        }

    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName() {
        _errorInputName.value = false
    }

    fun resetErrorInputCount() {
        _errorInputCount.value = false
    }

    private fun finishWork() {
        _shouldCloseScreen.value = Unit
    }
}