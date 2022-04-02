package info.diwe.training_app.viewmodel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.diwe.training_app.model.user.User
import info.diwe.training_app.model.user.UserDao
import kotlinx.coroutines.launch

class UserViewModel(private val dao: UserDao): ViewModel() {

    var newUserName = ""
    var newUserEmail = ""

    fun addUser() {
        viewModelScope.launch {
            val user = User()
            user.userName = newUserName
            user.userEmail = newUserEmail
            dao.create(user)
        }
    }
}