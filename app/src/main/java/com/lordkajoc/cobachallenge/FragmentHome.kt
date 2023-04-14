package com.lordkajoc.cobachallenge

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lordkajoc.cobachallenge.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentHome : Fragment() {

    lateinit var binding : FragmentHomeBinding
    lateinit var sharedPreferences : SharedPreferences
    var dbNote : RoomDatabaseNote? = null
    lateinit var adapterNote : AdapterNote
    lateinit var vmNote : ViewModelNote

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        var getUser = sharedPreferences.getString("user","")
        binding.tvWelcome.text = "Welcome, $getUser"

        dbNote = RoomDatabaseNote.getInstance(requireContext())

        noteVM()
        vmNote = ViewModelProvider(this).get(ViewModelNote::class.java)

//        vmNote.getAllNoteObservers().observe(viewLifecycleOwner{
//            adapterNote.setNoteData(it as ArrayList<RoomDataNote>)
//        })
        vmNote.getAllNoteObservers().observe(viewLifecycleOwner) {
            adapterNote.setData(it as ArrayList<RoomDataNote>)
        }

        binding.btnAddNote.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_fragmentInputData)
        }

        binding.tvLogout.setOnClickListener{
            var pref = sharedPreferences.edit()
            pref.clear()
            pref.clear()
            findNavController().navigate(R.id.action_homeFragment_to_fragmentLogin)
        }
    }

    //methods
    fun noteVM(){
        adapterNote = AdapterNote(ArrayList())
        binding.rvHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvHome.adapter = adapterNote
    }

    fun getAllNote(){

        GlobalScope.launch {
            var data = dbNote?.noteDao()?.getNote()
            activity?.runOnUiThread {
                adapterNote = AdapterNote(data!!)
                binding.rvHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvHome.adapter = adapterNote
            }
        }

    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var data =dbNote?.noteDao()?.getNote()

            activity?.runOnUiThread {
                adapterNote = AdapterNote(data!!)
                binding.rvHome.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvHome.adapter = adapterNote
            }
        }
    }

}