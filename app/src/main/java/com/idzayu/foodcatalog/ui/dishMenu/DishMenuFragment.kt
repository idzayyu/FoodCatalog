package com.idzayu.foodcatalog.ui.dishMenu

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.idzayu.foodcatalog.R
import com.idzayu.foodcatalog.adapters.DishAdapter
import com.idzayu.foodcatalog.databinding.FragmentDishMenuBinding
import com.idzayu.foodcatalog.repository.Dish
import com.idzayu.foodcatalog.repository.Repo
import com.idzayu.foodcatalog.ui.detailDish.DetailDishFragment
import kotlin.collections.ArrayList
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.distinct
import kotlin.collections.iterator
import kotlin.collections.set


class DishMenuFragment() : Fragment(), LifecycleOwner {

    private lateinit var viewModel: DishMenuViewModel
    lateinit var rw: RecyclerView
    lateinit var rwAdapter: DishAdapter
    lateinit var binding: FragmentDishMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDishMenuBinding.inflate(inflater)

        binding.materialToolbar.title = arguments?.getString("title") ?: "Категория"
        binding.materialToolbar.isTitleCentered = true
        binding.materialToolbar.setNavigationOnClickListener {
            binding.root.findNavController().navigate(R.id.action_dishMenuFragment_pop_including_navigation_home)
        }

        val lst = ArrayList<String>()
        for (i in Repo.dishes) {
            lst.addAll(i.tags)
        }
        for (i in lst.distinct()) {
            addChip(i)
        }

        init(binding)
        return binding.root
    }

    @SuppressLint("InflateParams")
    private fun addChip(str: String) {
        val chip = this.layoutInflater.inflate(
            com.idzayu.foodcatalog.R.layout.item_chip_tag,
            null,
            false
        ) as Chip

        chip.text = str
        Repo.filterDish[str] = false

        if (str == "Все меню") {
            chip.isSelected = true
        }

        chip.setOnCheckedChangeListener { buttonView, isChecked ->

            val lst = ArrayList<Dish>()
            val lstFilter = ArrayList<String>()
            if (chip.text == "Все меню") {
                binding.chipGroup.clearCheck()
                lst.addAll(Repo.dishes)
                rwAdapter.updateAdapter(lst)

            } else {
                Repo.filterDish[str] = chip.isChecked

                for ((key, value) in Repo.filterDish) {
                    if (value) {
                        lstFilter.add(key)
                    }
                }
                for (i in Repo.dishes) {
                    for (tag in i.tags) {
                        if (lstFilter.contains(tag)) {
                            lst.add(i)
                            break
                        }
                    }
                }

                if (lstFilter.size == 0){
                    lst.addAll(Repo.dishes)
                }
                rwAdapter.updateAdapter(lst)
            }
        }

        binding.chipGroup.addView(chip)

    }

    private fun init(binding: FragmentDishMenuBinding) {
        viewModel = ViewModelProvider(this)[DishMenuViewModel::class.java]

        rw = binding.rw
        val lst = ArrayList<Dish>()
        lst.addAll(Repo.dishes)
        rwAdapter = DishAdapter(lst, object : DishAdapter.NewsClickListener {
            override fun onCategoryDetailClicked(dish: Dish) {
                val detailFragment = DetailDishFragment(dish)
                detailFragment.show(requireActivity().supportFragmentManager, "dialog")
            }
        })
        val layoutManager = GridLayoutManager(requireActivity(), 3)

        rw.layoutManager = layoutManager
        rw.adapter = rwAdapter
    }

}