package com.idzayu.foodcatalog.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idzayu.foodcatalog.R
import com.idzayu.foodcatalog.adapters.CategoryAdapter
import com.idzayu.foodcatalog.databinding.FragmentHomeBinding
import com.idzayu.foodcatalog.repository.*
import com.idzayu.foodcatalog.repository.Repo.categories
import com.idzayu.foodcatalog.repository.Repo.dishes
import com.idzayu.foodcatalog.ui.basket.BasketFragment
import com.idzayu.foodcatalog.ui.dishMenu.DishMenuFragment
import com.idzayu.foodcatalog.ui.search.SearchFragment

class HomeFragment : Fragment(), LifecycleOwner {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    lateinit var rw: RecyclerView
    lateinit var rwAdapter: CategoryAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        rw = binding.rw

        rwAdapter = CategoryAdapter(categories, object: CategoryAdapter.NewsClickListener {
            override fun onCategoryDetailClicked(category: Category) {
                val detailFragment = DishMenuFragment(category)
                requireActivity().supportFragmentManager.commit {
                    add(R.id.nav_host_fragment_activity_main,detailFragment)
                    addToBackStack("Main")
                }
                //Toast.makeText(requireContext(),"Нажал:)",Toast.LENGTH_SHORT).show()
            }
        })
        rw.layoutManager = LinearLayoutManager(requireContext())
        populateList()
        rw.adapter = rwAdapter

        return root
    }

    private fun populateList() {
        ApiCategory().getCategory(object : ApiCategory.MyCallback{
            override fun onSuccess(result: CategoryModel) {
                categories.addAll(result.categories)
                rwAdapter.updateAdapter(result.categories)
            }

            override fun onFailure(error: Throwable) {
                Log.d("ForecastList",error.message, error)

            }

        })

        ApiDish().getCategory(object : ApiDish.MyCallback{
            override fun onSuccess(result: DishModel) {
                dishes.addAll(result.dishes)
            }

            override fun onFailure(error: Throwable) {
                Log.d("ForecastList",error.message, error)
            }

        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}