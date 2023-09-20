package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class AccountRoom(
    @PrimaryKey var email: String,
    var firstName: String,
    var lastName: String,
    var password: String
)

@Dao
interface AccountDao {
    @Query("SELECT * FROM AccountRoom")
    fun getAll(): LiveData<List<AccountRoom>>

    @Insert
    fun insertAll(vararg AccountRoom: AccountRoom)

    @Query("SELECT (SELECT COUNT(*) FROM AccountRoom) == 0")
    fun isEmpty(): Boolean

    @Query("SELECT (SELECT COUNT(*) FROM AccountRoom WHERE ( email = :email AND password = :password ) ) != 0")
    suspend fun validateLogin(email: String, password: String): Boolean
}

@Database(entities = [AccountRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun AccountDao(): AccountDao
}
