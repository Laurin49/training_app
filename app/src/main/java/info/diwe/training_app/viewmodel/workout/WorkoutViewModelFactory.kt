package info.diwe.training_app.viewmodel.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.diwe.training_app.model.workout.WorkoutDao

@Suppress("UNCHECKED_CAST")
class WorkoutViewModelFactory(private val dao: WorkoutDao): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WorkoutViewModel::class.java)) {
            return WorkoutViewModel(dao) as T
        }
        throw IllegalArgumentException("WorkoutViewModelFactory Exception: Unknown ViewModel")
    }
}