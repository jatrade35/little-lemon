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
    fun insertAll(vararg accountRoom: AccountRoom)

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

@Entity
data class MenuItemRoom(
    @PrimaryKey var id: Int,
    var title: String,
    var description: String,
    var price: Int,
    var imageURL: String,
    var category: String
)

@Dao
interface MenuItemDao {
    @Insert
    fun insertAll(vararg menuItem: MenuItemRoom)

    @Query("SELECT * FROM MenuItemRoom")
    fun getAll(): LiveData<List<MenuItemRoom>>
    @Query("SELECT (SELECT COUNT(*) FROM MenuItemRoom) == 0")
    fun isEmpty(): Boolean
}

@Database(entities = [AccountRoom::class, MenuItemRoom::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun AccountDao(): AccountDao
    abstract fun MenuItemDao(): MenuItemDao
}
