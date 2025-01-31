package com.soukou.contactsapp

import androidx.room.*
import androidx.lifecycle.LiveData

@Entity(tableName = "contacts")
data class Contact (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val phoneNumber: String,
    val email: String
)

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts ORDER BY lastName ASC")
    fun getAllContacts(): LiveData<List<Contact>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(contact: Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)
}


