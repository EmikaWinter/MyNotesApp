package com.example.an16.mynotesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.an16.mynotesapp.R
import com.example.an16.mynotesapp.databinding.FragmentMainBinding
import com.example.an16.mynotesapp.ui.home.HomeFragment
import com.example.an16.mynotesapp.ui.search.SearchFragment

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .replace(R.id.child_container, HomeFragment())
            .commit()

        binding?.bottomNavigationMenu?.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.child_container, HomeFragment())
                        .commit()
                    true
                }

                R.id.search -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.child_container, SearchFragment())
                        .commit()
                    true
                }

                R.id.profile -> {
                    childFragmentManager.beginTransaction()
                        .replace(R.id.child_container, ProfileFragment())
                        .commit()
                    true
                }

                else -> false
            }

        }
    }
}