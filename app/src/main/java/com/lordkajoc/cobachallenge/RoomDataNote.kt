package com.lordkajoc.cobachallenge

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class RoomDataNote(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var title : String,
    var content : String
): Serializable