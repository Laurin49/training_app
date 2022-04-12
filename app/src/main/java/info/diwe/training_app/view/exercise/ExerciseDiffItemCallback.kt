package info.diwe.training_app.view.exercise

import androidx.recyclerview.widget.DiffUtil
import info.diwe.training_app.model.exercise.Exercise

class ExerciseDiffItemCallback: DiffUtil.ItemCallback<Exercise>() {
    override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean =
        oldItem.exerciseId == newItem.exerciseId

    override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean =
        oldItem == newItem
}