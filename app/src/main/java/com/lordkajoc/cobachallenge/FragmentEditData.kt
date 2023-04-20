package com.lordkajoc.cobachallenge

//Sample Fragment jika tidak menggunakan DialogFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.lordkajoc.cobachallenge.databinding.FragmentEditDataBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class FragmentEditData : Fragment() {
//    lateinit var binding:FragmentEditDataBinding
//    var dbNote : RoomDatabaseNote? = null
//    lateinit var vmNote : ViewModelNote
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        binding = FragmentEditDataBinding.inflate(layoutInflater,container,false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        dbNote = RoomDatabaseNote.getInstance(requireContext())
//        vmNote = ViewModelProvider(this).get(ViewModelNote::class.java)
//
//        var getData = arguments?.getSerializable("note") as RoomDataNote
//        binding.etTitle.setText(getData.title)
//        binding.etContent.setText(getData.content)
//
//        binding.btnEdit.setOnClickListener{
//            EditNote()
//            Navigation.findNavController(view).navigate(R.id.action_fragmentEditData_to_homeFragment)
//            Toast.makeText(context, "Edit note berhasil", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    fun EditNote(){
//        GlobalScope.async {
//            var getData = arguments?.getSerializable("note") as RoomDataNote
//            var title = binding.etTitle.text.toString()
//            var content = binding.etContent.text.toString()
//
//            val editNote = RoomDataNote(getData.id, title, content)
//            vmNote.updateNote(editNote)
//        }
//    }
}