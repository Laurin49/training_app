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
import info.diwe.training_app.R
import info.diwe.training_app.databinding.FragmentExerciseBinding
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

        var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        var currentDate = Date().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
        binding.edtWorkoutDatum.setText(currentDate.format(formatter))

        binding.btnDataPicker.setOnClickListener {
            val dpickerFragment = DatePickerFragment(binding)
            activity?.let { it1 ->
                dpickerFragment.show(it1.supportFragmentManager, "datePicker")
            }
        }

        binding.edtWorkoutDatum.setOnClickListener {
            val dpickerFragment = DatePickerFragment(binding)
            activity?.let { it1 ->
                dpickerFragment.show(it1.supportFragmentManager, "datePicker")
            }
        }
        return view
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    class DatePickerFragment(val binding: FragmentExerciseBinding): DialogFragment(), DatePickerDialog.OnDateSetListener {

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

            binding.edtWorkoutDatum.setText(currentDate.format(formatter))
            binding.tvWorkoutDatum.setText(currentDate.format(formatter))
            Toast.makeText(context, "Datum: $date", Toast.LENGTH_LONG).show()
        }

    }
}

