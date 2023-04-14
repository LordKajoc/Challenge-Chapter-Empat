package com.lordkajoc.cobachallenge

import androidx.room.*

@Dao
interface RoomDaoNote {

    @Insert
    fun insertNote(note : RoomDataNote)

    @Query("SELECT * FROM RoomDataNote ORDER BY id DESC ")
    fun getNote() : List<RoomDataNote>

    @Delete
    fun deleteNote (note: RoomDataNote)

    @Update
    fun updateNote(note: RoomDataNote)

}