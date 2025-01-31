package com.soukou.contactsapp

import android.content.Context


@Database(entities = [Contact::class], version = 1, exportSchema = false)
    abstract class ContactDatabase : RoomDatabase() {
        abstract  fun contactDao() : ContactDao

        companion object {
            @Volatile
            private var INSTANCE:ContactDatabase? = null

            fun getDatabase(context: Context): ContactDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contact_database"

                    ).build()
                    INSTANCE = instance
                    Log.d("ContactDatabase", "Database created")
                    instance
                }
            }
        }
    }