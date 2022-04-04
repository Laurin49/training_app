package info.diwe.training_app.view.user

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.MaterialToolbar
import info.diwe.training_app.R
import info.diwe.training_app.databinding.FragmentUsersBinding
import info.diwe.training_app.model.TrainingAppDatabase
import info.diwe.training_app.viewmodel.user.UserViewModel
import info.diwe.training_app.viewmodel.user.UserViewModelFactory

class UsersFragment : Fragment() {

    private var _binding: FragmentUsersBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        val view = binding.root

        setToolBarMenu(binding.toolbarUser)

        // Build the database, (if it doesn't already exist) and get a reference to the
        // userDao property
        val application = requireNotNull(this.activity).application
        val dao = TrainingAppDatabase.getInstance(application).userDao


        val viewModelFactory = UserViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(UserViewModel::class.java)

        binding.viewModel = viewModel
        // make a layout respond to live data updates -> by setting the layout's lifecycle owner
        binding.lifecycleOwner = viewLifecycleOwner

        return view
    }

    private fun setToolBarMenu(toolbarUser: MaterialToolbar) {
        toolbarUser.inflateMenu(R.menu.user_toolbar)
        // binding.toolbarUser.setNavigationIcon(resources.getDrawable(R.drawable.ic_action_back))
        toolbarUser.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addUserFragment -> {
                    Toast.makeText(activity, "Add User Toolbar selected", Toast.LENGTH_LONG).show()
                    true
                }
                else -> {
                    true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}