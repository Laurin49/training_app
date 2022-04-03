package info.diwe.training_app.viewmodel.user

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.diwe.training_app.model.user.User
import info.diwe.training_app.model.user.UserDao
import kotlinx.coroutines.launch

class UserViewModel(private val dao: UserDao): ViewModel() {

    var newUserName = ""
    var newUserEmail = ""

    private val userList = dao.readAll()
    val usersAsString = Transformations.map(userList) {
        userList -> formatUserList(userList)
    }

    private fun formatUserList(userList: List<User>): String {
        return userList.fold("") {
            str, item -> str + '\n' + formatUser(item)
        }
    }

    private fun formatUser(user: User): String {
        var strUser = "ID: ${user.userId}"
        strUser += " - " + "Name: ${user.userName}"
        strUser += " - " + "Email: ${user.userEmail}" + '\n'
        return strUser
    }

    fun addUser() {
        viewModelScope.launch {
            val user = User()
            user.userName = newUserName
            user.userEmail = newUserEmail
            dao.create(user)
        }
    }
}