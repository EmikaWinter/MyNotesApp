package com.example.an16.mynotesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    private var items: List<ViewPagerModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.vp_items, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerAdapter.ViewHolder, position: Int) {
        holder.bindContent(items.get(position))
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
        val title: TextView = itemView.findViewById(R.id.text_vp)
        val image: ImageView = itemView.findViewById(R.id.image_vp)

        fun bindContent(viewPagerModel: ViewPagerModel) {
            title.setText(viewPagerModel.title)
//            or setBackgroundRes
            image.setImageResource(viewPagerModel.image)

        }
    }

}