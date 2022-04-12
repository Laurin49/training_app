package info.diwe.training_app.viewmodel.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import info.diwe.training_app.model.exercise.ExerciseDao
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class ExerciseViewModelFactory(private val dao: ExerciseDao): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExerciseViewModel::class.java)) {
            return ExerciseViewModel(dao) as T
        } else {
            throw IllegalArgumentException("ExerciseViewModel Exception: Unknown ViewModel")
        }
    }
}