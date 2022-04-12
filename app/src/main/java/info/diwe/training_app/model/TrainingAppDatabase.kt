package info.diwe.training_app.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import info.diwe.training_app.model.exercise.Exercise
import info.diwe.training_app.model.exercise.ExerciseDao
import info.diwe.training_app.model.user.User
import info.diwe.training_app.model.user.UserDao
import info.diwe.training_app.model.workout.Workout
import info.diwe.training_app.model.workout.WorkoutDao

@Database(entities = [User::class, Workout::class, Exercise::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TrainingAppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao

    companion object {
        @Volatile
        private var INSTANCE: TrainingAppDatabase? = null

        fun getInstance(context: Context): TrainingAppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext, TrainingAppDatabase::class.java, "training.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}