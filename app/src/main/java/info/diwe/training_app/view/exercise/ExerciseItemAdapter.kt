package info.diwe.training_app.view.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import info.diwe.training_app.databinding.RvExerciseCardviewBinding
import info.diwe.training_app.model.exercise.Exercise

class ExerciseItemAdapter(val clickListener: (exerciseId: Long, mode: String) -> Unit):
    ListAdapter<Exercise, ExerciseItemAdapter.ExerciseItemViewHolder>(ExerciseDiffItemCallback()) {

    class ExerciseItemViewHolder(val binding: RvExerciseCardviewBinding):
        RecyclerView.ViewHolder(binding.root) {
            companion object {
                fun inflateFrom(parent: ViewGroup): ExerciseItemViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = RvExerciseCardviewBinding.inflate(layoutInflater, parent, false)
                    return ExerciseItemViewHolder(binding)
                }
            }

            fun bind(item: Exercise, clickListener: (exerciseId: Long, mode: String) -> Unit) {
                binding.exercise = item
                binding.btnExerciseEdit.setOnClickListener { clickListener(item.exerciseId, "edit") }
                binding.btnExerciseRemove.setOnClickListener { clickListener(item.exerciseId, "delete") }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseItemViewHolder {
        return ExerciseItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: ExerciseItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
}