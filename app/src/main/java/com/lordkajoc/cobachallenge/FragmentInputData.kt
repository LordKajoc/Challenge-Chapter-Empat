package com.lordkajoc.cobachallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import com.lordkajoc.cobachallenge.databinding.FragmentInputDataBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class FragmentInputData : DialogFragment() {

    lateinit var binding : FragmentInputDataBinding
    var dbNote : RoomDatabaseNote? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentInputDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dbNote = RoomDatabaseNote.getInstance(requireContext())

        binding.btnTambah.setOnClickListener{
            addNote()

//            Navigation.findNavController(view).navigate(R.id.action_fragmentInputData_to_homeFragment)
        }
    }

    fun addNote(){
        GlobalScope.async {
            var title = binding.titlenote.text.toString()
            var content = binding.contentnote.text.toString()
            dbNote!!.noteDao().insertNote(RoomDataNote(0, title, content))
            Toast.makeText(context, "Tambah Note berhasil", Toast.LENGTH_SHORT).show()
        }
        dismiss()
//        finish()
    }
    override fun onDetach() {
        super.onDetach()
        activity?.recreate()
    }
}