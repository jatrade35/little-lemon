package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class AccountRoom(
    @PrimaryKey var email: String,
    var profilePictureId: Int,
    var firstName: String,
    var lastName: String,
    var password: String
)

@Dao
interface AccountDao {
    @Insert
    fun insertAll(vararg AccountRoom: AccountRoom)

    @Query("SELECT * FROM AccountRoom")
    fun getAll(): LiveData<List<AccountRoom>>

    @Query("SELECT * FROM AccountRoom WHERE email = :email")
    suspend fun getAccount(email: String): AccountRoom

    @Query("SELECT (SELECT COUNT(*) FROM AccountRoom) == 0")
    fun isEmpty(): Boolean

    @Query("SELECT (SELECT COUNT(*) FROM AccountRoom WHERE ( email = :email AND password = :password ) ) != 0")
    fun validateLogin(email: String, password: String): Boolean

    @Delete
    suspend fun deleteAccount(account: AccountRoom)
}

@Database(entities = [AccountRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun AccountDao(): AccountDao
}
