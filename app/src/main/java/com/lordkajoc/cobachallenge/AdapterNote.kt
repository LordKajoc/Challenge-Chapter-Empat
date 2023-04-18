package com.lordkajoc.cobachallenge

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.lordkajoc.cobachallenge.databinding.ItemListBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.ArrayList

class AdapterNote (var listNote : List<RoomDataNote>) : RecyclerView.Adapter<AdapterNote.ViewHolder> () {
    var dbNote : RoomDatabaseNote? = null
    class ViewHolder (var binding : ItemListBinding) : RecyclerView.ViewHolder(binding.root){
        fun dataBinding(itemData : RoomDataNote){
            binding.note = itemData
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.dataBinding(listNote[position])
        holder.binding.btnDeleteNote.setOnClickListener{
            dbNote = RoomDatabaseNote.getInstance(it.context)
            Toast.makeText(it.context, "Note Telah Dihapus", Toast.LENGTH_SHORT).show()
            GlobalScope.async {
                ViewModelNote(Application()).deleteNote(listNote[position])
                dbNote?.noteDao()?.deleteNote(listNote[position])
                val nav = Navigation.findNavController(it)
                nav.run{
                    navigate(R.id.homeFragment)
                }
            }
        }

        holder.binding.btnEditNote.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("note", listNote[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_fragmentEditData, bundle)

        }
    }


    override fun getItemCount(): Int {
        return  listNote.size
    }

    fun setData(listNote: ArrayList<RoomDataNote>)
    {
        this.listNote=listNote
    }
}