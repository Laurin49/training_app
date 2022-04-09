package info.diwe.training_app.model.workout

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WorkoutDao {
    @Insert
    suspend fun createWorkout(workout: Workout)
    @Update
    suspend fun updateWorkout(workout: Workout)
    @Delete
    suspend fun deleteWorkout(workout: Workout)

    @Query("DELETE FROM workouts WHERE workoutId = :workoutId")
    suspend fun deleteWorkoutById(workoutId: Long)

    @Query("SELECT * FROM workouts WHERE workoutId = :workoutId")
    fun readWorkout(workoutId: Long): LiveData<Workout>

    @Query("SELECT * FROM workouts ORDER BY workoutId DESC")
    fun readWorkouts(): LiveData<List<Workout>>
}