package info.diwe.training_app.view.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import info.diwe.training_app.R
import info.diwe.training_app.databinding.FragmentCreateWorkoutBinding
import info.diwe.training_app.databinding.FragmentWorkoutBinding
import info.diwe.training_app.model.TrainingAppDatabase
import info.diwe.training_app.viewmodel.workout.WorkoutViewModel
import info.diwe.training_app.viewmodel.workout.WorkoutViewModelFactory

class CreateWorkoutFragment : Fragment() {

    private var _binding: FragmentCreateWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentCreateWorkoutBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TrainingAppDatabase.getInstance(application).workoutDao()

        val viewModelFactory = WorkoutViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
                .get(WorkoutViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnSaveNewWorkout.setOnClickListener {
            val workoutname = binding.edtNewWorkoutname.text.toString()
            if (!workoutname.isEmpty()) {
                viewModel.addWorkout(workoutname)
                this.findNavController().navigate(R.id.action_createWorkoutFragment_to_workoutFragment)
            } else {
                binding.edtNewWorkoutname.requestFocus()
                Toast.makeText(application.applicationContext, "Workout-Name ist erforderlich !",
                        Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCancelNewWorkout.setOnClickListener {
            this.findNavController().navigate(R.id.action_createWorkoutFragment_to_workoutFragment)
        }
        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}