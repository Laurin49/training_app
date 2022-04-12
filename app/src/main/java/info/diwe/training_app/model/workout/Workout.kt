package info.diwe.training_app.model.workout

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "workouts")
data class Workout(
        @PrimaryKey(autoGenerate = true)
        var workoutId: Long = 0L,
        @ColumnInfo(name = "workout_name")
        var workoutName: String = "",
        @ColumnInfo(name = "workout_datum")
        var workoutDatum: Date? = null
)
