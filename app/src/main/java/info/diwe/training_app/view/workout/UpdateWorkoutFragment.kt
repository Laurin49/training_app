package info.diwe.training_app.view.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import info.diwe.training_app.R
import info.diwe.training_app.databinding.FragmentUpdateWorkoutBinding
import info.diwe.training_app.model.TrainingAppDatabase
import info.diwe.training_app.viewmodel.workout.WorkoutEditViewModel
import info.diwe.training_app.viewmodel.workout.WorkoutEditViewModelFactory
import info.diwe.training_app.viewmodel.workout.WorkoutViewModel
import info.diwe.training_app.viewmodel.workout.WorkoutViewModelFactory


class UpdateWorkoutFragment : Fragment() {

    private var _binding: FragmentUpdateWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentUpdateWorkoutBinding.inflate(inflater, container, false)
        val view = binding.root

        val workoutId = UpdateWorkoutFragmentArgs.fromBundle(requireArguments()).workoutId

        val application = requireNotNull(this.activity).application
        val dao = TrainingAppDatabase.getInstance(application).workoutDao()

        val viewModelFactory = WorkoutEditViewModelFactory(workoutId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(WorkoutEditViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToWorkoutList.observe(viewLifecycleOwner, Observer { navigate ->
            if (navigate) {
                view.findNavController().navigate(R.id.action_updateWorkoutFragment_to_workoutFragment)
                viewModel.onNavigateToWorkoutList()
            }
        })

        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}