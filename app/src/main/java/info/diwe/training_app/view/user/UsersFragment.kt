package info.diwe.training_app.view.user

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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


        binding.btnNewUser.setOnClickListener {
            val dialog_view = layoutInflater.inflate(R.layout.dialog_new_user, null)
            val edt_Name = dialog_view.findViewById<EditText>(R.id.edt_user_username)
            val edt_Mail = dialog_view.findViewById<EditText>(R.id.edt_user_email)

            with(AlertDialog.Builder(it.context)) {
                setView(dialog_view)
                setTitle(getString(R.string.title_add_new_user))
                setPositiveButton(getString(R.string.btn_save),
                        DialogInterface.OnClickListener{ dialog, id ->
                    save_new_user(viewModel, edt_Name.text.toString(), edt_Mail.text.toString())
                    dialog.dismiss()
                })
                setNegativeButton(getString(R.string.btn_cancel),
                        DialogInterface.OnClickListener{ dialog, id ->
                    cancel_new_user()
                    dialog.cancel()
                })
                show()
            }
        }

        return view
    }

    private fun cancel_new_user() {
        Toast.makeText(context, "Dialog New User Cancelled ...", Toast.LENGTH_LONG).show()
    }

    private fun save_new_user(viewModel: UserViewModel, newUser: String, newEmail: String) {
        if (newUser.isNotEmpty() && newEmail.isNotEmpty()) {
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(newEmail).matches()) {
                Toast.makeText(context, "Bitte geben Sie eine gültige Email an !!!",
                        Toast.LENGTH_LONG).show()
            } else {
                viewModel.addUser(newUser, newEmail)
                Toast.makeText(context, "Neuer Benutzer: ${newUser}, Neue Email: $newEmail",
                        Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "Bitte geben Sie sowohl einen Namen als auch eine Email an !!!",
                    Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}