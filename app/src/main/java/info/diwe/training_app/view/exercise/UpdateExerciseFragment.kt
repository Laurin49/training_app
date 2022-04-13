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
import info.diwe.training_app.databinding.FragmentUpdateExerciseBinding
import info.diwe.training_app.model.TrainingAppDatabase
import info.diwe.training_app.model.exercise.Exercise
import info.diwe.training_app.viewmodel.exercise.ExerciseViewModel
import info.diwe.training_app.viewmodel.exercise.ExerciseViewModelFactory
import kotlinx.coroutines.*

class UpdateExerciseFragment : Fragment() {

    private var _binding: FragmentUpdateExerciseBinding? = null
    val binding get() = _binding!!
    var aktExercise: Exercise? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateExerciseBinding.inflate(inflater, container, false)
        val view = binding.root

        val exerciseId = UpdateExerciseFragmentArgs.fromBundle(requireArguments()).exerciseId


        val application = requireNotNull(this.activity).application
        val dao = TrainingAppDatabase.getInstance(application).exerciseDao()

        val viewModelFactory = ExerciseViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
                .get(ExerciseViewModel::class.java)

        binding.exerciseUpdateVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        CoroutineScope(Dispatchers.Main).launch {
            var currExercise: Exercise? = null
            withContext(Dispatchers.IO) {
                currExercise = dao.readExerciseById(exerciseId)
            }
            if (currExercise != null) {
                aktExercise = currExercise
                Toast.makeText(requireContext(), "Curr Exercise Name: ${aktExercise?.exerciseName}", Toast.LENGTH_LONG).show()
                binding.edtUpdateExerciseName.setText(aktExercise?.exerciseName)
                binding.edtUpdateExerciseCategory.setText(aktExercise?.exerciseCategory)
            }
        }

        binding.btnSaveUpdateExercise.setOnClickListener {
            val updateExerciseName = binding.edtUpdateExerciseName.text.toString()
            val updateExerciseCategory = binding.edtUpdateExerciseCategory.text.toString()
            when {
                updateExerciseName.isEmpty() -> {
                    binding.edtUpdateExerciseName.requestFocus()
                    Toast.makeText(requireContext(), "Exercise Name must not be empty ...",
                        Toast.LENGTH_LONG).show()
                }
                updateExerciseCategory.isEmpty() -> {
                    binding.edtUpdateExerciseCategory.requestFocus()
                    Toast.makeText(requireContext(), "Exercise Category must not be empty ...",
                        Toast.LENGTH_LONG).show()
                }
                else -> {
                    aktExercise?.exerciseName = updateExerciseName
                    aktExercise?.exerciseCategory = updateExerciseCategory
                    viewModel.updateExercise(aktExercise!!)
                    this.findNavController().navigate(R.id.action_updateExerciseFragment_to_exerciseFragment)
                }
            }
        }

        binding.btnCancelUpdateExercise.setOnClickListener {
            this.findNavController().navigate(R.id.action_updateExerciseFragment_to_exerciseFragment)
        }

        return view
    }

}