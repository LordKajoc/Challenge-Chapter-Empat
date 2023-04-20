package com.lordkajoc.cobachallenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewModelNote(app: Application) : AndroidViewModel(app) {

    var allNote: MutableLiveData<List<RoomDataNote>> = MutableLiveData()

    init {
        getAllNote()
    }

    fun getAllNoteObservers(): MutableLiveData<List<RoomDataNote>> {
        return allNote
    }

    fun getAllNote() {
        GlobalScope.launch {
            val userDao = RoomDatabaseNote.getInstance(getApplication())!!.noteDao()
            val listNote = userDao.getNoteAsc()
            allNote.postValue(listNote)
        }
    }

    fun insertNote(entity: RoomDataNote) {
        val noteDao = RoomDatabaseNote.getInstance(getApplication())?.noteDao()
        noteDao!!.insertNote(entity)
        getAllNote()
    }

    fun deleteNote(entity: RoomDataNote) {
        val userDao = RoomDatabaseNote.getInstance(getApplication())!!.noteDao()
        userDao?.deleteNote(entity)
        getAllNote()
    }

    fun updateNote(entity: RoomDataNote) {
        val userDao = RoomDatabaseNote.getInstance(getApplication())!!.noteDao()
        userDao?.updateNote(entity)
        getAllNote()
    }

}