package info.diwe.training_app.view.user

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import info.diwe.training_app.R
import info.diwe.training_app.model.user.User

class UserItemAdapter: RecyclerView.Adapter<UserItemAdapter.UserItemViewHolder>() {

    var data = listOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class UserItemViewHolder(val rootView: CardView): RecyclerView.ViewHolder(rootView) {

        val userName = rootView.findViewById<TextView>(R.id.tv_user_name)
        val userEmail = rootView.findViewById<TextView>(R.id.tv_user_email)

        companion object {
            fun inflateFrom(parent: ViewGroup): UserItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                        .inflate(R.layout.rv_user_cardview, parent, false) as CardView
                return UserItemViewHolder(view)
            }
        }
        fun bind(item: User) {
            userName.text = item.userName
            userEmail.text = item.userEmail
        }
    }
}