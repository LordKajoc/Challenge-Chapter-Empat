package com.lordkajoc.cobachallenge

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.lordkajoc.cobachallenge.databinding.FragmentHomeScreenBinding

class FragmentHomeScreen : Fragment(){
    lateinit var binding: FragmentHomeScreenBinding
    lateinit var menuInflater: MenuInflater
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imagefilter = binding.chip1
        registerForContextMenu(imagefilter)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_filter,menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.option_ascending -> Toast.makeText(context,"Ascend Filter", Toast.LENGTH_LONG).show()
            R.id.option_descending -> Toast.makeText(context,"Descend Filter", Toast.LENGTH_LONG).show()
        }
        return super.onContextItemSelected(item)
    }
}