package com.idzayu.foodcatalog.ui.home

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.*
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.location.*
import com.idzayu.foodcatalog.R
import com.idzayu.foodcatalog.adapters.CategoryAdapter
import com.idzayu.foodcatalog.databinding.FragmentHomeBinding
import com.idzayu.foodcatalog.repository.*
import com.idzayu.foodcatalog.repository.Repo.categories
import com.idzayu.foodcatalog.ui.dishMenu.DishMenuFragment
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment(), LifecycleOwner {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    lateinit var rw: RecyclerView
    lateinit var rwAdapter: CategoryAdapter
    private lateinit var homeViewModel: HomeViewModel

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
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

        rwAdapter = CategoryAdapter(categories, object : CategoryAdapter.NewsClickListener {
            override fun onCategoryDetailClicked(category: Category) {
                Repo.params[3] = category.name
                binding.root.findNavController().navigate(R.id.action_navigation_home_to_dishMenuFragment)

            }
        })
        rw.layoutManager = LinearLayoutManager(requireContext())
        populateList()
        rw.adapter = rwAdapter


        Repo.params[2] = SimpleDateFormat("dd MMMM, yyyy", Locale("ru")).format(Date())

        binding.homeMaterialToolbar.title = Repo.params[1]

        Repo.params[1]?.let { homeViewModel.setLocality(it) }
        Repo.params[2]?.let { binding.homeMaterialToolbar.subtitle = it }

        return root
    }


    private fun populateList() {
        ApiCategory().getCategory(object : ApiCategory.MyCallback {
            override fun onSuccess(result: CategoryModel) {
                categories.addAll(result.categories)
                rwAdapter.updateAdapter(result.categories)
            }

            override fun onFailure(error: Throwable) {
                Log.d("ForecastList", error.message, error)
            }
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}