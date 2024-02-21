package com.example.an16.mynotesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.databinding.FragmentFirstPageBinding

class FirstPageFragment : Fragment() {

    private var binding: FragmentFirstPageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstPageBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.openButton?.setOnClickListener {
            findNavController().navigate(R.id.action_firstPageFragment_to_onboardingFragment)
        }
        binding?.loginTextView?.setOnClickListener {
            findNavController().navigate(R.id.action_firstPageFragment_to_loginFragment)
        }
    }
}