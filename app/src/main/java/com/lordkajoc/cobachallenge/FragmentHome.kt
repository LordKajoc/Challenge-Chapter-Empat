package com.lordkajoc.cobachallenge

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.lordkajoc.cobachallenge.databinding.FragmentHomeBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentHome : Fragment() {

    lateinit var binding: FragmentHomeBinding
    lateinit var sharedPreferences: SharedPreferences
    var dbNote: RoomDatabaseNote? = null
    lateinit var adapterNote: AdapterNote
    lateinit var vmNote: ViewModelNote

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

        (activity as AppCompatActivity).setSupportActionBar(binding.tbHome)

        sharedPreferences = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        var getUser = sharedPreferences.getString("user", "")
        binding.tbHome.title = "Welcome, $getUser"

        dbNote = RoomDatabaseNote.getInstance(requireContext())

        noteVM()
        vmNote = ViewModelProvider(this).get(ViewModelNote::class.java)

//        vmNote.getAllNoteObservers().observe(viewLifecycleOwner{
//            adapterNote.setNoteData(it as ArrayList<RoomDataNote>)
//        })
        vmNote.getAllNoteObservers().observe(viewLifecycleOwner) {
            adapterNote.setData(it as ArrayList<RoomDataNote>)
        }

        binding.btnAddNote.setOnClickListener {
            FragmentInputData().show(childFragmentManager, "InputDialogFragment")
        }

        binding.btnlogout.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("LOGOUT")
                .setMessage("Yakin ingin logout?")
                .setNegativeButton("Tidak"){ dialogInterface: DialogInterface, i: Int ->
                    dialogInterface.dismiss()
                }.setPositiveButton("Ya") { dialogInterface: DialogInterface, i: Int ->
                    val pref = sharedPreferences.edit()
                    pref.clear()
                    pref.apply()
                    Toast.makeText(context, "Logout Berhasil", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_homeFragment_to_fragmentLogin)
                }.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_filter, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.option_ascending -> {
                getAllNoteAsc()
                Toast.makeText(context, "Sorted by Ascending", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.option_descending -> {
                getAllNoteDesc()
                Toast.makeText(context, "Sorted by Descending", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    //methods
    fun noteVM() {
        adapterNote = AdapterNote(ArrayList())
        binding.rvHome.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvHome.adapter = adapterNote
    }

    fun getAllNoteAsc() {
        GlobalScope.launch {
            var data = dbNote?.noteDao()?.getNoteAsc()
            activity?.runOnUiThread {
                adapterNote = AdapterNote(data!!)
                binding.rvHome.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvHome.adapter = adapterNote
            }
        }
    }

    fun getAllNoteDesc() {
        GlobalScope.launch {
            var data = dbNote?.noteDao()?.getNoteDesc()
            activity?.runOnUiThread {
                adapterNote = AdapterNote(data!!)
                binding.rvHome.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvHome.adapter = adapterNote
            }
        }
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            var data = dbNote?.noteDao()?.getNoteAsc()
            activity?.runOnUiThread {
                adapterNote = AdapterNote(data!!)
                binding.rvHome.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                binding.rvHome.adapter = adapterNote
            }
        }
    }
}