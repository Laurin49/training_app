package info.diwe.training_app.model.exercise

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercises")
data class Exercise(
        @PrimaryKey(autoGenerate = true)
        var exerciseId: Long = 0L,
        @ColumnInfo(name = "exercise_name")
        var exerciseName: String = "",
        @ColumnInfo(name = "exercise_category")
        var exerciseCategory: String = ""
)