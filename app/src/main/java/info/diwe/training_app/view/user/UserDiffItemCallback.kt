package info.diwe.training_app.view.user

import androidx.recyclerview.widget.DiffUtil
import info.diwe.training_app.model.user.User

class UserDiffItemCallback: DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean
        = (oldItem.userId == newItem.userId)

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean = (oldItem == newItem)
}