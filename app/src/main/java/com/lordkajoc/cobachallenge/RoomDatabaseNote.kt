package com.lordkajoc.cobachallenge

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomDataNote::class], version = 1)
abstract class RoomDatabaseNote : RoomDatabase() {

    abstract fun noteDao(): RoomDaoNote

    companion object {
        private var INSTANCE: RoomDatabaseNote? = null

        fun getInstance(context: Context): RoomDatabaseNote? {
            if (INSTANCE == null) {
                synchronized(RoomDatabaseNote::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RoomDatabaseNote::class.java, "Note.db"
                    ).build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }


    }
}