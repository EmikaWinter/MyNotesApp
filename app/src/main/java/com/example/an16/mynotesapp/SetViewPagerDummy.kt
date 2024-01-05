package com.example.an16.mynotesapp

class SetViewPagerDummy {
    companion object{
        fun setDataPager() : ArrayList<ViewPagerModel>{
            val list = ArrayList<ViewPagerModel>()
            list.add(ViewPagerModel(R.string.write, R.drawable.logo_chat))
            list.add(ViewPagerModel(R.string.compose, R.drawable.logo_man_look))
            list.add(ViewPagerModel(R.string.memorize, R.drawable.logo_girl_file))
            return list
        }
    }
}