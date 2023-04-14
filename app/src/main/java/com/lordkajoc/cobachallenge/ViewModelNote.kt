package com.lordkajoc.cobachallenge

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewModelNote(app : Application): AndroidViewModel(app) {

    lateinit var allStudent : MutableLiveData<List<RoomDataNote>>

    init{
        allStudent = MutableLiveData()
        getAllStudent()
    }
    fun getAllStudentObservers(): MutableLiveData<List<RoomDataNote>> {
        return allStudent
    }

    fun getAllStudent() {
        GlobalScope.launch {
            val userDao = RoomDatabaseNote.getInstance(getApplication())!!.studentDao()
            val listStudet = userDao.getDataStudent()
            allStudent.postValue(listStudet)
        }
    }

    fun insertStudent(entity: RoomDataNote){
        val studentDao = RoomDatabaseNote.getInstance(getApplication())?.studentDao()
        studentDao!!.insertStudent(entity)
        getAllStudent()
    }

    fun deleteStudent(entity: RoomDataNote){
        val userDao = RoomDatabaseNote.getInstance(getApplication())!!.studentDao()
        userDao?.deleteStudent(entity)
        getAllStudent()
    }

    fun updateStudent(entity: RoomDataNote){
        val userDao = RoomDatabaseNote.getInstance(getApplication())!!.studentDao()
        userDao?.updateStudent(entity)
        getAllStudent()
    }

}