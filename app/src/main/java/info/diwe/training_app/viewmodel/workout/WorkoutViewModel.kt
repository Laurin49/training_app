package info.diwe.training_app.viewmodel.workout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.diwe.training_app.model.workout.Workout
import info.diwe.training_app.model.workout.WorkoutDao
import kotlinx.coroutines.launch
import java.util.*

class WorkoutViewModel(private val dao: WorkoutDao): ViewModel() {

    val workoutList = dao.readWorkouts()

    fun addWorkout(workoutName: String) {
        viewModelScope.launch {
            val workout = Workout()
            workout.workoutDatum = Date()
            workout.workoutName = workoutName
            dao.createWorkout(workout)
        }
    }

    fun addWorkout(workoutName: String, workoutDatum: Date) {
        viewModelScope.launch {
            val workout = Workout()
            workout.workoutDatum = workoutDatum
            workout.workoutName = workoutName
            dao.createWorkout(workout)
        }
    }

    fun deleteWorkout(workoutId: Long) {
        viewModelScope.launch {
            dao.deleteWorkoutById(workoutId)
        }
    }

    private val _navigateToWorkout = MutableLiveData<Long?>()
    val navigateToWorkout: LiveData<Long?>
        get() = _navigateToWorkout

    fun onWorkoutClicked(workoutId: Long) { _navigateToWorkout.value = workoutId }
    fun onWorkoutNavigated() { _navigateToWorkout.value = null }
}