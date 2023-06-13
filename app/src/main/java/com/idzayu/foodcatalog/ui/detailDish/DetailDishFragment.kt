package com.idzayu.foodcatalog.ui.detailDish

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.idzayu.foodcatalog.databinding.FragmentDetailDishBinding
import com.idzayu.foodcatalog.repository.Dish
import com.idzayu.foodcatalog.repository.Repo

class DetailDishFragment(private val dish: Dish) : DialogFragment()  {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailDishBinding.inflate(inflater)
        init(binding)
        dialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    private fun init(binding: FragmentDetailDishBinding){

        binding.apply {
            name.text = dish.name
            price.text = dish.price + "₽"
            weightDish.text = " · " + dish.weight + "г"

            if (dish.id != 4) {
                Glide.with(ArtDish)
                    .load(dish.imageUrl)
                    .into(ArtDish)

                description.text = dish.description
            }
            else{
                description.text = "Вкусно!"
                Glide.with(ArtDish)
                    .load(dish.description)
                    .into(ArtDish)
            }

            like.setOnClickListener{
            }
            buttonAddBasket.setOnClickListener{
                if (Repo.dishesBasket.contains(dish.id)){
                    Repo.dishesBasket[dish.id]?.cntBasket  = Repo.dishesBasket[dish.id]?.cntBasket?.plus(
                        1
                    )!!
                }else{
                    Repo.dishesBasket[dish.id] = dish
                    Repo.dishesBasket[dish.id]?.cntBasket  = Repo.dishesBasket[dish.id]?.cntBasket?.plus(
                        1
                    )!!
                }

                dismiss()
            }
            close.setOnClickListener {
                dismiss()
            }
        }
    }

}