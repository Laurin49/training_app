package info.diwe.training_app.view.workout

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
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
import info.diwe.training_app.databinding.FragmentCreateWorkoutBinding
import info.diwe.training_app.databinding.FragmentExerciseBinding
import info.diwe.training_app.databinding.FragmentWorkoutBinding
import info.diwe.training_app.model.TrainingAppDatabase
import info.diwe.training_app.view.exercise.ExerciseFragment
import info.diwe.training_app.viewmodel.workout.WorkoutViewModel
import info.diwe.training_app.viewmodel.workout.WorkoutViewModelFactory
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class CreateWorkoutFragment : Fragment() {

    private var _binding: FragmentCreateWorkoutBinding? = null
    val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentCreateWorkoutBinding.inflate(inflater, container, false)
        val view = binding.root

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        var currentDate = Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        binding.tvNewWorkoutdatum.setText(currentDate.format(formatter))

        val application = requireNotNull(this.activity).application
        val dao = TrainingAppDatabase.getInstance(application).workoutDao()

        val viewModelFactory = WorkoutViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
                .get(WorkoutViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.tvNewWorkoutdatum.setOnClickListener {
            val dpickerFragment = DatePickerFragment(binding)
            activity?.let { it1 ->
                dpickerFragment.show(it1.supportFragmentManager, "datePicker")
            }
        }

        binding.btnSelectDatum.setOnClickListener {
            val dpickerFragment = DatePickerFragment(binding)
            activity?.let { it1 ->
                dpickerFragment.show(it1.supportFragmentManager, "datePicker")
            }
        }

        binding.btnSaveNewWorkout.setOnClickListener {
            val workoutname = binding.edtNewWorkoutname.text.toString()
            val edt_workoutdatum = binding.tvNewWorkoutdatum.text.toString()

            var currentDate = LocalDate.parse(edt_workoutdatum, formatter)
            var workoutdatum = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant())

            if (!workoutname.isEmpty()) {
                viewModel.addWorkout(workoutname, workoutdatum)
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

class DatePickerFragment(val binding: FragmentCreateWorkoutBinding): DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        return DatePickerDialog(this.requireContext(), this, year, month, day)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val cal: Calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Berlin"))
        cal.set(year, month, day)
        cal.add(Calendar.HOUR, 2)
        var date: Date = cal.time

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        var currentDate = date
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()

        binding.tvNewWorkoutdatum.setText(currentDate.format(formatter))
        Toast.makeText(context, "Datum: $date", Toast.LENGTH_LONG).show()
    }

}