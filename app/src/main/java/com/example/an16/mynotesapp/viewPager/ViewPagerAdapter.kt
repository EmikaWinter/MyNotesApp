package com.example.an16.mynotesapp.viewPager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.an16.mynotesapp.R

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private var items: List<ViewPagerModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.vp_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindContent(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setViewPagerAdapter(viewPagerModel: List<ViewPagerModel>){
        items = viewPagerModel
    }

    class ViewHolder constructor(
        view: View
    ) : RecyclerView.ViewHolder(view) {

        private val title: TextView = itemView.findViewById(R.id.text_vp)
        private val image: ImageView = itemView.findViewById(R.id.image_vp)

        fun bindContent(viewPagerModel: ViewPagerModel) {
            title.setText(viewPagerModel.title)
            image.setImageResource(viewPagerModel.image)
        }
    }

    companion object {
        fun setDataPager(): ArrayList<ViewPagerModel> {
            val list = ArrayList<ViewPagerModel>()
            list.add(ViewPagerModel(R.string.write, R.drawable.logo_chat))
            list.add(ViewPagerModel(R.string.compose, R.drawable.logo_man_look))
            list.add(ViewPagerModel(R.string.memorize, R.drawable.logo_girl_file))
            return list
        }
    }
}