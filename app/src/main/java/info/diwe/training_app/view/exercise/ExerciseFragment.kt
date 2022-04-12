package info.diwe.training_app.view.exercise

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import info.diwe.training_app.R
import info.diwe.training_app.databinding.FragmentExerciseBinding
import info.diwe.training_app.model.TrainingAppDatabase
import info.diwe.training_app.viewmodel.exercise.ExerciseViewModel
import info.diwe.training_app.viewmodel.exercise.ExerciseViewModelFactory
import java.time.Instant
import java.time.LocalDate
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class ExerciseFragment : Fragment() {

    private var _binding: FragmentExerciseBinding? = null
    val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExerciseBinding.inflate(inflater, container, false)
        val view = binding.root

        // Build the database, (if it doesn't already exist) and get a reference to the
        // userDao property
        val application = requireNotNull(this.activity).application
        val dao = TrainingAppDatabase.getInstance(application).exerciseDao()

        val viewModelFactory = ExerciseViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ExerciseViewModel::class.java)

        binding.exerciseVM = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = ExerciseItemAdapter{ exerciseId, mode ->
            if (mode == "edit") {

            } else {

            }
        }
        binding.rvExerciseList.adapter = adapter

        viewModel.exerciseList.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.btnNewExercise.setOnClickListener {
            this.findNavController().navigate(R.id.action_exerciseFragment_to_addExerciseFragment)
        }

        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}