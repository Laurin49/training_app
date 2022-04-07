package info.diwe.training_app.view.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import info.diwe.training_app.R
import info.diwe.training_app.databinding.FragmentUserEditBinding
import info.diwe.training_app.model.TrainingAppDatabase
import info.diwe.training_app.viewmodel.user.UserEditViewModel
import info.diwe.training_app.viewmodel.user.UserEditViewModelFactory

class UserEditFragment : Fragment() {

    private var _binding: FragmentUserEditBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentUserEditBinding.inflate(inflater, container, false)
        val view = binding.root

        val userId = UserEditFragmentArgs.fromBundle(requireArguments()).userId

        val application = requireNotNull(this.activity).application
        val dao = TrainingAppDatabase.getInstance(application).userDao

        val viewModelFactory = UserEditViewModelFactory(userId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(UserEditViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController().navigate(R.id.action_userEditFragment_to_usersFragment)
                viewModel.onNavigatedToList()
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}