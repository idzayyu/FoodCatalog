package com.idzayu.foodcatalog.ui.dishMenu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idzayu.foodcatalog.R
import com.idzayu.foodcatalog.adapters.DishAdapter
import com.idzayu.foodcatalog.databinding.FragmentDishMenuBinding
import com.idzayu.foodcatalog.repository.Category
import com.idzayu.foodcatalog.repository.Dish
import com.idzayu.foodcatalog.repository.Repo

class DishMenuFragment(category: Category) : Fragment() {

    private lateinit var viewModel: DishMenuViewModel
    lateinit var rw: RecyclerView
    lateinit var rwAdapter: DishAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDishMenuBinding.inflate(inflater)
        init(binding)
        return binding.root
    }
    private fun init(binding: FragmentDishMenuBinding){
        viewModel = ViewModelProvider(this)[DishMenuViewModel::class.java]

        rw = binding.rw

        Toast.makeText(requireContext(),Repo.dishes.size.toString(), Toast.LENGTH_SHORT).show()
        rwAdapter = DishAdapter(Repo.dishes, object: DishAdapter.NewsClickListener {
            override fun onCategoryDetailClicked(dish: Dish) {
                //val detailFragment = DishMenuFragment(dish)
                //requireActivity().supportFragmentManager.beginTransaction()
                //    .replace(R.id.nav_host_fragment_activity_main,detailFragment)
                //    .addToBackStack("Main")
                //    .commit()
            }
        })
        val layoutManager = GridLayoutManager(requireActivity(),3)
        rw.layoutManager = layoutManager
        rw.adapter = rwAdapter
    }

}