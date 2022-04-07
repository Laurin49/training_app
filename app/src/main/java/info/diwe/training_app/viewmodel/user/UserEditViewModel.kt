package info.diwe.training_app.viewmodel.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.diwe.training_app.model.user.UserDao
import kotlinx.coroutines.launch

class UserEditViewModel(val userId: Long, val dao: UserDao): ViewModel() {

    val user = dao.read(userId)

    fun updateUser() {
        viewModelScope.launch {
            dao.update(user.value!!)
            _navigateToList.value = true
        }
    }

    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}