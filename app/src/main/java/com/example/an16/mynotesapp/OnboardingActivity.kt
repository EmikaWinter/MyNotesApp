package com.example.an16.mynotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import me.relex.circleindicator.CircleIndicator3

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        findViewById<TextView>(R.id.skip).setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        setData()

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = viewPagerAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val circleIndicator = findViewById<CircleIndicator3>(R.id.circle_indicator)
        circleIndicator.setViewPager(viewPager)
    }

    private fun setData(){
        viewPagerAdapter = ViewPagerAdapter()
        val data = SetViewPagerDummy.setDataPager()
        viewPagerAdapter.setViewPagerAdapter(data)
    }
}