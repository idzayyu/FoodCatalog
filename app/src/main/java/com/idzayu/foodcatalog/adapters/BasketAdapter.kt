package com.idzayu.foodcatalog.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idzayu.foodcatalog.R
import com.idzayu.foodcatalog.databinding.BasketItemBinding
import com.idzayu.foodcatalog.repository.Dish
import com.idzayu.foodcatalog.repository.Repo

class BasketAdapter(private val partList: ArrayList<Dish>, private val listener: NewsClickListener
) : RecyclerView.Adapter<BasketAdapter.BasketHolder>() {


    inner class BasketHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = BasketItemBinding.bind(item)

        @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
        fun bind(
            dish: Dish,
            listener: NewsClickListener
        ) = with(binding) {
            name.text = dish.name
            cntBasket.text = dish.cntBasket.toString()


            plus.setOnClickListener {
                binding.cntBasket.text = listener.onPlusClicked(dish)
            }

            priceDish.text = dish.price + "₽"
            weightDish.text = " · " + dish.weight + "г"

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

            minus.setOnClickListener {
                binding.cntBasket.text = listener.onMinusClicked(dish)

                if (dish.cntBasket == 0 ){
                    removeAdapter(dish)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.basket_item, parent, false)
        return BasketHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeAdapter(dish: Dish){
        // обновляем список
        Repo.dishesBasket.remove(dish.id)
        notifyItemRemoved(partList.indexOf(dish))
        partList.remove(dish)
    }


    override fun onBindViewHolder(holder: BasketHolder, position: Int) {
        if (partList.isNotEmpty()){
            holder.bind(partList[position], listener)
        }
    }

    override fun getItemCount(): Int {
        return partList.size
    }

    interface NewsClickListener {
        fun onPlusClicked(dish: Dish): String
        fun onMinusClicked(dish: Dish): String
    }
}