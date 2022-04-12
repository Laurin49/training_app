package info.diwe.training_app.view.workout

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import info.diwe.training_app.R
import info.diwe.training_app.databinding.FragmentUpdateWorkoutBinding
import info.diwe.training_app.model.TrainingAppDatabase
import info.diwe.training_app.viewmodel.workout.WorkoutEditViewModel
import info.diwe.training_app.viewmodel.workout.WorkoutEditViewModelFactory
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class UpdateWorkoutFragment : Fragment() {

    private var _binding: FragmentUpdateWorkoutBinding? = null
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
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

        var akt_datum: Date = Date()
        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        viewModel.workout.observe(viewLifecycleOwner, Observer { workout ->
            var currentDate = workout.workoutDatum!!.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
            currentDate.also { akt_datum = Date.from(it.atStartOfDay(ZoneId.systemDefault()).toInstant()) }
            binding.tvUpdateWorkoutdatum.setText(currentDate.format(formatter))
        })

        binding.btnUpdateDatum.setOnClickListener {
            val dpickerFragment = UpdateDatePickerFragment(binding, akt_datum)
            activity?.let { it1 ->
                dpickerFragment.show(it1.supportFragmentManager, "datePicker")
            }
        }

        binding.btnSaveUpdateWorkout.setOnClickListener {
            val updateWorkoutName = binding.edtUpdateWorkoutname.text.toString()
            val updateWorkoutDatum = binding.tvUpdateWorkoutdatum.text.toString()

            var currentDate = LocalDate.parse(updateWorkoutDatum, formatter)
            var workoutdatum = Date.from(currentDate.atStartOfDay(ZoneId.systemDefault()).toInstant())

            viewModel.updateWorkout(updateWorkoutName, workoutdatum)
        }

        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}

class UpdateDatePickerFragment(val binding: FragmentUpdateWorkoutBinding, val datum: Date): DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        c.time = datum
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

        binding.tvUpdateWorkoutdatum.setText(currentDate.format(formatter))
    }

}