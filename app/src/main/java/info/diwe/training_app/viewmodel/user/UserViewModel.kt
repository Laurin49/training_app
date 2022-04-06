package info.diwe.training_app.viewmodel.user

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}