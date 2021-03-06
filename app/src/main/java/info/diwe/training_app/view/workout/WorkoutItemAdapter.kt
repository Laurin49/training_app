package info.diwe.training_app.view.workout

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import info.diwe.training_app.databinding.RvWorkoutCardviewBinding
import info.diwe.training_app.model.workout.Workout
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

class WorkoutItemAdapter(val clickListener: (workoutId: Long, mode: String) -> Unit):
    ListAdapter<Workout, WorkoutItemAdapter.WorkoutItemViewHolder>(WorkoutDiffItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutItemViewHolder {
        return WorkoutItemViewHolder.inflateFrom(parent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
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
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(item: Workout, clickListener: (workoutId: Long, mode: String) -> Unit) {

            binding.workout = item


            var formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val edt_workoutdatum = item.workoutDatum

            var currentDate = edt_workoutdatum!!.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate()
            Log.d("ItemAdapter", "Item Date: ${currentDate.format(formatter)}")

            binding.tvWorkoutDatum.setText(currentDate.format(formatter))
            binding.btnWorkoutEdit.setOnClickListener { clickListener(item.workoutId, "edit") }
            binding.btnWorkoutRemove.setOnClickListener {clickListener(item.workoutId, "delete") }
        }
    }
}