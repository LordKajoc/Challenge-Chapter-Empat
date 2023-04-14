package com.lordkajoc.cobachallenge

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.lordkajoc.cobachallenge.databinding.FragmentSplashScreenBinding

class FragmentSplashScreen : Fragment() {
    lateinit var binding : FragmentSplashScreenBinding
    lateinit var sharedSplash : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashScreenBinding.inflate(layoutInflater)
        val view = binding.root

        Handler(Looper.myLooper()!!).postDelayed({

            sharedSplash = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

            if(sharedSplash.getString("user", "").equals("")){
                findNavController().navigate(R.id.action_fragmentSplashScreen_to_fragmentLogin)
            }else if (sharedSplash.getString("user", "")!!.isNotEmpty()){
                findNavController().navigate(R.id.action_fragmentSplashScreen_to_homeFragment)
            }
        }, 3000)
        return view
    }

}