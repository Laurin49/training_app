package info.diwe.training_app.viewmodel.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import info.diwe.training_app.model.exercise.Exercise
import info.diwe.training_app.model.exercise.ExerciseDao
import kotlinx.coroutines.launch

class ExerciseViewModel(private val dao: ExerciseDao): ViewModel() {

    val exerciseList = dao.readExercises()

    fun addExercise(name: String, category: String) {
        viewModelScope.launch {
            val exercise = Exercise()
            exercise.exerciseName = name
            exercise.exerciseCategory = category
            dao.createExercise(exercise)
        }
    }

    fun deleteExercise(id: Long) {
        viewModelScope.launch {
            dao.deleteExerciseById(id)
        }
    }

    fun updateExercise(exercise: Exercise) {
        viewModelScope.launch {
            dao.updateExercise(exercise)
        }
    }

}