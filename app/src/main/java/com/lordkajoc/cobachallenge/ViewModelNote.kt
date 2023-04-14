package com.lordkajoc.cobachallenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewModelNote(app : Application): AndroidViewModel(app) {

    lateinit var allNote : MutableLiveData<List<RoomDataNote>>

    init{
        allNote = MutableLiveData()
        getAllStudent()
    }
    fun getAllStudentObservers(): MutableLiveData<List<RoomDataNote>> {
        return allNote
    }

    fun getAllStudent() {
        GlobalScope.launch {
            val userDao = RoomDatabaseNote.getInstance(getApplication())!!.noteDao()
            val listNote = userDao.getNote()
            allNote.postValue(listNote)
        }
    }

    fun insertStudent(entity: RoomDataNote){
        val noteDao = RoomDatabaseNote.getInstance(getApplication())?.noteDao()
        noteDao!!.insertNote(entity)
        getAllStudent()
    }

    fun deleteStudent(entity: RoomDataNote){
        val userDao = RoomDatabaseNote.getInstance(getApplication())!!.noteDao()
        userDao?.deleteNote(entity)
        getAllStudent()
    }

    fun updateStudent(entity: RoomDataNote){
        val userDao = RoomDatabaseNote.getInstance(getApplication())!!.noteDao()
        userDao?.updateNote(entity)
        getAllStudent()
    }

}