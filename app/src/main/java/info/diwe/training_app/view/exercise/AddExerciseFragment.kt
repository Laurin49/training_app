package info.diwe.training_app.view.exercise

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import info.diwe.training_app.R
import info.diwe.training_app.databinding.FragmentAddExerciseBinding
import info.diwe.training_app.model.TrainingAppDatabase
import info.diwe.training_app.viewmodel.exercise.ExerciseViewModel
import info.diwe.training_app.viewmodel.exercise.ExerciseViewModelFactory

class AddExerciseFragment : Fragment() {

    private var _binding: FragmentAddExerciseBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentAddExerciseBinding.inflate(inflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TrainingAppDatabase.getInstance(application).exerciseDao()

        val viewModelFactory = ExerciseViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
                .get(ExerciseViewModel::class.java)

        binding.addExerciseVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.btnSaveNewExercise.setOnClickListener {

            when {
                binding.edtNewExerciseName.text.toString().isEmpty() -> {
                    binding.edtNewExerciseName.requestFocus()
                    Toast.makeText(requireContext(), "ExerciseName should not be empty ...",
                            Toast.LENGTH_LONG).show()
                }
                binding.edtNewExerciseCategory.text.toString().isEmpty() -> {
                    binding.edtNewExerciseCategory.requestFocus()
                    Toast.makeText(requireContext(), "ExerciseCategory should not be empty ...",
                            Toast.LENGTH_LONG).show()
                }
                else -> {
                    val currExerciseName = binding.edtNewExerciseName.text.toString()
                    val currExerciseCategory = binding.edtNewExerciseCategory.text.toString()

                    viewModel.addExercise(currExerciseName, currExerciseCategory)

                    this.findNavController().navigate(R.id.action_addExerciseFragment_to_exerciseFragment)
                }
            }
        }

        binding.btnCancelNewExercise.setOnClickListener {
            this.findNavController().navigate(R.id.action_addExerciseFragment_to_exerciseFragment)
        }

        return view
    }

}