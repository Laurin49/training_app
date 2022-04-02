package info.diwe.training_app.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import info.diwe.training_app.model.user.User
import info.diwe.training_app.model.user.UserDao

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class TrainingAppDatabase: RoomDatabase() {
    abstract val userDao: UserDao

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