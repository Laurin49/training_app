package info.diwe.training_app.viewmodel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.diwe.training_app.model.user.UserDao
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class UserViewModelFactory(private val dao: UserDao): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dao) as T
        }
        throw IllegalArgumentException("UserViewModelFactory Exception: Unknown ViewModel")
    }
}