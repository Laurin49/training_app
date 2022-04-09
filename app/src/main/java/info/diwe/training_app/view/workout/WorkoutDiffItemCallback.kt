package info.diwe.training_app.view.workout

import androidx.recyclerview.widget.DiffUtil
import info.diwe.training_app.model.workout.Workout

class WorkoutDiffItemCallback: DiffUtil.ItemCallback<Workout>() {
    override fun areItemsTheSame(oldItem: Workout, newItem: Workout): Boolean =
        (oldItem.workoutId == newItem.workoutId)

    override fun areContentsTheSame(oldItem: Workout, newItem: Workout): Boolean =
        (oldItem == newItem)
}