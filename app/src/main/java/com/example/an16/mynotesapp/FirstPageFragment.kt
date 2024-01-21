package com.example.an16.mynotesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, OnboardingFragment())
                .addToBackStack(null)
                .commit()
        }
        binding?.loginTextView?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, LoginFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    companion object {

        const val TAG = "FirstPageFragment"
    }
}