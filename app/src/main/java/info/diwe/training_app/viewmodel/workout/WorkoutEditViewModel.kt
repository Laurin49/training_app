package info.diwe.training_app.viewmodel.workout

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.diwe.training_app.model.workout.WorkoutDao
import kotlinx.coroutines.launch
import java.util.*

class WorkoutEditViewModel(val workoutId: Long, val dao: WorkoutDao): ViewModel() {

    val workout = dao.readWorkout(workoutId)

    fun updateWorkout() {
        viewModelScope.launch {
            dao.updateWorkout(workout.value!!)
            _navigateToWorkoutList.value = true
        }
    }

    fun updateWorkout(name: String, datum: Date) {
        viewModelScope.launch {
            workout.value?.workoutName = name
            workout.value?.workoutDatum = datum

             dao.updateWorkout(workout.value!!)
            _navigateToWorkoutList.value = true
        }
    }

    fun cancelUpdateWorkout() {
        _navigateToWorkoutList.value = true
    }

    private val _navigateToWorkoutList = MutableLiveData<Boolean>(false)
    val navigateToWorkoutList: LiveData<Boolean>
        get() = _navigateToWorkoutList

    fun onNavigateToWorkoutList() { _navigateToWorkoutList.value = false }
}