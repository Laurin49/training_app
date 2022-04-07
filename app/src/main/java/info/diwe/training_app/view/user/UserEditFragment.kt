package info.diwe.training_app.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import info.diwe.training_app.R

class UserEditFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_user_edit, container, false)

        val tvEditUserId = view.findViewById<TextView>(R.id.tv_edit_user_id)
        val userId = UserEditFragmentArgs.fromBundle(requireArguments()).userId
        tvEditUserId.text = userId.toString()

        return view
    }

}