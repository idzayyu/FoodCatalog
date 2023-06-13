package com.idzayu.foodcatalog.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idzayu.foodcatalog.R
import com.idzayu.foodcatalog.databinding.CategoryItemBinding
import com.idzayu.foodcatalog.repository.Category

class CategoryAdapter(private val partList: ArrayList<Category>, private val listener: NewsClickListener
) : RecyclerView.Adapter<CategoryAdapter.ForecastDayHolder>() {


    var categories: ArrayList<Category> = partList

    inner class ForecastDayHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = CategoryItemBinding.bind(item)

        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(
            category: Category,
            listener: NewsClickListener
        ) = with(binding) {
            name.text = category.name
            artCategory.setOnClickListener {
                listener.onCategoryDetailClicked(category)
            }

            Glide.with(artCategory)
                .load(category.imageUrl)
                .into(artCategory)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastDayHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ForecastDayHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(items: ArrayList<Category>){
        // обновляем список
        categories.clear()
        categories.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ForecastDayHolder, position: Int) {
        if (partList.isNotEmpty()){
            holder.bind(partList[position], listener)
        }
    }

    override fun getItemCount(): Int {
        return partList.size
    }

    interface NewsClickListener{
        fun onCategoryDetailClicked(category: Category)
    }
}