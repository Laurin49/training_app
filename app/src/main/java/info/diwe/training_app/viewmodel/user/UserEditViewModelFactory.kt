package info.diwe.training_app.viewmodel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.diwe.training_app.model.user.UserDao

class UserEditViewModelFactory(private val userId: Long, private val dao: UserDao):
        ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserEditViewModel::class.java)) {
            return UserEditViewModel(userId, dao) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}