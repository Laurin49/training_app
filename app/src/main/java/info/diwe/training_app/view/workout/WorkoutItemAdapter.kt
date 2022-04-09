package info.diwe.training_app.view.workout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import info.diwe.training_app.databinding.RvWorkoutCardviewBinding
import info.diwe.training_app.model.workout.Workout

class WorkoutItemAdapter(val clickListener: (workoutId: Long, mode: String) -> Unit):
    ListAdapter<Workout, WorkoutItemAdapter.WorkoutItemViewHolder>(WorkoutDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutItemViewHolder {
        return WorkoutItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: WorkoutItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class WorkoutItemViewHolder(val binding: RvWorkoutCardviewBinding): RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): WorkoutItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RvWorkoutCardviewBinding.inflate(layoutInflater, parent, false)
                return WorkoutItemViewHolder(binding)
            }
        }
        fun bind(item: Workout, clickListener: (workoutId: Long, mode: String) -> Unit) {
            binding.workout = item
            binding.btnWorkoutEdit.setOnClickListener { clickListener(item.workoutId, "edit") }
            binding.btnWorkoutRemove.setOnClickListener {clickListener(item.workoutId, "delete") }
        }
    }
}