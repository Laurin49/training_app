package info.diwe.training_app.viewmodel.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.diwe.training_app.model.workout.WorkoutDao

@Suppress("UNCHECKED_CAST")
class WorkoutEditViewModelFactory(private val workoutId: Long, private val dao: WorkoutDao):
        ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutEditViewModel::class.java)) {
            return WorkoutEditViewModel(workoutId, dao) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}