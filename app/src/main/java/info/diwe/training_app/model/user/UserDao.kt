package info.diwe.training_app.model.user

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert()
    suspend fun create(user: User)
    @Update()
    suspend fun update(user: User)
    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM users WHERE userId = :userId")
    suspend fun deleteUserById(userId: Long)

    @Query("SELECT * FROM users WHERE userId = :userId")
    fun read(userId: Long): LiveData<User>

    @Query("SELECT * FROM users ORDER BY userId DESC")
    fun readAll(): LiveData<List<User>>
}