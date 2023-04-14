package com.lordkajoc.cobachallenge

import androidx.room.*

@Dao
interface RoomDaoNote {

    @Insert
    fun insertStudent(student : RoomDataNote)

    @Query("SELECT * FROM RoomDataNote ORDER BY id DESC ")
    fun getDataStudent() : List<RoomDataNote>

    @Delete
    fun deleteStudent (note: RoomDataNote)

    @Update
    fun updateStudent(note: RoomDataNote)

}