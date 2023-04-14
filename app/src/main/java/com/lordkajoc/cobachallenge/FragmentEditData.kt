package com.lordkajoc.cobachallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lordkajoc.cobachallenge.databinding.FragmentEditDataBinding

class FragmentEditData : Fragment() {
    lateinit var binding:FragmentEditDataBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditDataBinding.inflate(layoutInflater,container,false)
        return binding.root
    }
}