package info.diwe.training_app.view.workout

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.MaterialToolbar
import info.diwe.training_app.R
import info.diwe.training_app.databinding.FragmentUsersBinding
import info.diwe.training_app.databinding.FragmentWorkoutBinding
import info.diwe.training_app.model.TrainingAppDatabase
import info.diwe.training_app.viewmodel.workout.WorkoutViewModel
import info.diwe.training_app.viewmodel.workout.WorkoutViewModelFactory

class WorkoutFragment : Fragment() {

    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        val view = binding.root

        // Build the database, (if it doesn't already exist) and get a reference to the
        // userDao property
        val application = requireNotNull(this.activity).application
        val dao = TrainingAppDatabase.getInstance(application).workoutDao()

        val viewModelFactory = WorkoutViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(WorkoutViewModel::class.java)

        binding.workoutVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = WorkoutItemAdapter{ workoutId, mode ->
            if (mode == "edit") {
                viewModel.onWorkoutClicked(workoutId)
            } else {
                // Workout löschen
                with(AlertDialog.Builder(requireContext())) {
                    setTitle(getString(R.string.dlg_title_workout_loeschen))
                    setPositiveButton("Ok", DialogInterface.OnClickListener{ dialog, _ ->
                        viewModel.deleteWorkout(workoutId)
                        dialog.dismiss()
                    })
                    setNegativeButton(getString(R.string.dlg_cancel), DialogInterface.OnClickListener {
                        dialog, _ ->
                            dialog.cancel()
                    })
                    show()
                }
            }
        }
        binding.rvWorkoutList.adapter = adapter

        viewModel.workoutList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        viewModel.navigateToWorkout.observe(viewLifecycleOwner, Observer { workoutId ->
            workoutId?.let {
                val action = WorkoutFragmentDirections.actionWorkoutFragmentToUpdateWorkoutFragment(workoutId)
                this.findNavController().navigate(action)
                viewModel.onWorkoutNavigated()
            }
        })

        binding.btnNewWorkout.setOnClickListener {
            this.findNavController().navigate(R.id.action_workoutFragment_to_createWorkoutFragment)
        }


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}