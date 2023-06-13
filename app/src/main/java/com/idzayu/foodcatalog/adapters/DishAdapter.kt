package com.idzayu.foodcatalog.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idzayu.foodcatalog.R
import com.idzayu.foodcatalog.databinding.CategoryItemBinding
import com.idzayu.foodcatalog.repository.Dish

class DishAdapter(private val partList: ArrayList<Dish>, private val listener: NewsClickListener
) : RecyclerView.Adapter<DishAdapter.DishHolder>() {


    inner class DishHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = CategoryItemBinding.bind(item)

        fun bind(
            dish: Dish,
            listener: NewsClickListener
        ) = with(binding) {
            name.text = dish.name
            artCategory.setOnClickListener {
                listener.onCategoryDetailClicked(dish)
            }
            if (dish.id != 4) {
                Glide.with(artCategory)
                    .load(dish.imageUrl)
                    .into(artCategory)
            }
            else{

                Glide.with(artCategory)
                    .load(dish.description)
                    .into(artCategory)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.dish_item, parent, false)
        return DishHolder(view)
    }

    override fun onBindViewHolder(holder: DishHolder, position: Int) {
         holder.bind(partList[position], listener)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateAdapter(items: ArrayList<Dish>){
        // обновляем список
        partList.clear()
        partList.addAll(items)
        notifyDataSetChanged()
    }



    override fun getItemCount(): Int {
        return partList.size
    }

    interface NewsClickListener {
        fun onCategoryDetailClicked(dish: Dish)
    }
}