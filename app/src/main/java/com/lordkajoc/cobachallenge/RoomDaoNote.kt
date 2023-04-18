package com.lordkajoc.cobachallenge

import androidx.room.*

@Dao
interface RoomDaoNote {

    @Insert
    fun insertNote(note : RoomDataNote)

    @Query("SELECT * FROM RoomDataNote ORDER BY title ASC ")
    fun getNoteAsc() : List<RoomDataNote>
    @Query("SELECT * FROM RoomDataNote ORDER BY title DESC ")
    fun getNoteDesc() : List<RoomDataNote>

    @Delete
    fun deleteNote (note: RoomDataNote)

    @Update
    fun updateNote(note: RoomDataNote)

}