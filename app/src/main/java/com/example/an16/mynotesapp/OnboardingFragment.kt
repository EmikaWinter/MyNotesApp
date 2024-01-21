package com.example.an16.mynotesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.an16.mynotesapp.databinding.FragmentOnboardingBinding
import com.example.an16.mynotesapp.viewPager.ViewPagerAdapter

class OnboardingFragment : Fragment() {

    private var binding: FragmentOnboardingBinding? = null

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(layoutInflater)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.skip?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, SignupFragment())
                .addToBackStack(null)
                .commit()
        }

        setData()

        val viewPager = binding?.viewPager
        viewPager?.adapter = viewPagerAdapter
        viewPager?.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val circleIndicator = binding?.circleIndicator
        circleIndicator?.setViewPager(viewPager)
    }

    private fun setData(){
        viewPagerAdapter = ViewPagerAdapter()
        val data = ViewPagerAdapter.setDataPager()
        viewPagerAdapter.setViewPagerAdapter(data)
    }
}