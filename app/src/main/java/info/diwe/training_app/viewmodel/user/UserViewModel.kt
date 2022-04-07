package info.diwe.training_app.viewmodel.user

import androidx.lifecycle.*
import info.diwe.training_app.model.user.User
import info.diwe.training_app.model.user.UserDao
import kotlinx.coroutines.launch

class UserViewModel(private val dao: UserDao): ViewModel() {

    val userList = dao.readAll()

    fun addUser(username: String, useremail: String) {
        viewModelScope.launch {
            val user = User()
            user.userName = username
            user.userEmail = useremail
            dao.create(user)
        }
    }

    private val _navigateToUser = MutableLiveData<Long?>()
    val navigateToUser: LiveData<Long?>
        get() = _navigateToUser

    fun onUserClicked(userId: Long) { _navigateToUser.value = userId }
    fun onUserNavigated() { _navigateToUser.value = null }
}