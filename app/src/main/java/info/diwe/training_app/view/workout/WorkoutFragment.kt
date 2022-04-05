package info.diwe.training_app.view.workout

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import info.diwe.training_app.R
import info.diwe.training_app.databinding.FragmentUsersBinding
import info.diwe.training_app.databinding.FragmentWorkoutBinding

class WorkoutFragment : Fragment() {

    private var _binding: FragmentWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.workoutToolbar.inflateMenu(R.menu.workout_toolbar)

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}