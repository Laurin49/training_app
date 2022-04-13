package info.diwe.training_app.model.exercise

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ExerciseDao {

    @Insert
    suspend fun createExercise(exercise: Exercise)
    @Update
    suspend fun updateExercise(exercise: Exercise)
    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Query("DELETE FROM exercises WHERE exerciseId = :exerciseId")
    suspend fun deleteExerciseById(exerciseId: Long)

    @Query("SELECT * FROM exercises WHERE exerciseId = :exerciseId")
    suspend fun readExerciseById(exerciseId: Long): Exercise

    @Query("SELECT * FROM exercises WHERE exerciseId = :exerciseId")
    fun readExercise(exerciseId: Long): LiveData<Exercise>

    @Query("SELECT * FROM exercises ORDER BY exerciseId DESC")
    fun readExercises(): LiveData<List<Exercise>>
}