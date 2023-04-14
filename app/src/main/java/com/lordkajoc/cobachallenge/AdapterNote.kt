package com.lordkajoc.cobachallenge

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
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

            GlobalScope.async {
                ViewModelNote(Application()).deleteStudent(listNote[position])
                dbNote?.noteDao()?.deleteNote(listNote[position])
//                val del = dbNote?.noteDao()?.deleteNote(listNote[position])
//                (holder.itemView.context as HomeFragment)
//                    (holder.itemView.context as HomeFragment).getAllNote()
                val nav = Navigation.findNavController(it)
                nav.run{
                    navigate(R.id.homeFragment)
                }
            }
        }

        holder.binding.btnEditNote.setOnClickListener {
//            var move = Intent(it.context, FragmentHomeScreenEditDataBinding::class.java)
//            move.putExtra("dataedit", listStudent[position])
//            it.context.startActivity(move)
            val bundle = Bundle()
            bundle.putSerializable("note", listNote[position])
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_fragmentEditData, bundle)

        }
//        holder.binding.klik.setOnClickListener{
//            var detail = Intent(it.context, DetailNoteActivity::class.java)
//            detail.putExtra("detail", listNote[position])
//            it.context.startActivity(detail)
//        }
    }


    override fun getItemCount(): Int {
        return  listNote.size
    }

    fun setData(listStudent: ArrayList<RoomDataNote>)
    {
        this.listNote=listStudent
    }
}