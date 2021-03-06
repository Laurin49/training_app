package info.diwe.training_app.view.user

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import info.diwe.training_app.R
import info.diwe.training_app.databinding.RvUserCardviewBinding
import info.diwe.training_app.model.user.User

class UserItemAdapter(val clickListener: (userId: Long, mode: String) -> Unit)
    : ListAdapter<User, UserItemAdapter.UserItemViewHolder>(UserDiffItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class UserItemViewHolder(val binding: RvUserCardviewBinding): RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun inflateFrom(parent: ViewGroup): UserItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RvUserCardviewBinding.inflate(layoutInflater, parent, false)
                return UserItemViewHolder(binding)
            }
        }
        fun bind(item: User, clickListener: (userId: Long, mode: String) -> Unit) {
            binding.user = item
            binding.btnUserEdit.setOnClickListener { clickListener(item.userId, "edit") }
            binding.btnUserRemove.setOnClickListener {clickListener(item.userId, "delete") }
        }
    }
}