package com.idzayu.foodcatalog.ui.basket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.idzayu.foodcatalog.adapters.BasketAdapter
import com.idzayu.foodcatalog.databinding.FragmentBasketBinding
import com.idzayu.foodcatalog.repository.Dish
import com.idzayu.foodcatalog.repository.Repo
import com.idzayu.foodcatalog.ui.home.HomeViewModel

class BasketFragment : Fragment() {

    private var _binding: FragmentBasketBinding? = null

    private val binding get() = _binding!!
    lateinit var rw: RecyclerView
    lateinit var rwAdapter: BasketAdapter
    private lateinit var homeViewModel: HomeViewModel
    private val lst = ArrayList<Dish>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val basketViewModel =
            ViewModelProvider(this)[BasketViewModel::class.java]

        _binding = FragmentBasketBinding.inflate(inflater, container, false)

        rw = binding.recyclerView
        lst.addAll(Repo.dishesBasket.values)
        rwAdapter = BasketAdapter(lst, object: BasketAdapter.NewsClickListener {
            override fun onMinusClicked(dish: Dish): String {
                Repo.dishesBasket[dish.id]?.cntBasket = Repo.dishesBasket[dish.id]?.cntBasket!! - 1
                binding.button.text = Repo.getPurchaseAmount()
                return (dish.cntBasket).toString()
            }
            override fun onPlusClicked(dish: Dish): String {
                Repo.dishesBasket[dish.id]?.cntBasket = Repo.dishesBasket[dish.id]?.cntBasket!! + 1
                binding.button.text = Repo.getPurchaseAmount()
                return (dish.cntBasket).toString()
            }
        })
        rw.layoutManager = LinearLayoutManager(requireContext())
        rw.adapter = rwAdapter

        binding.button.text = Repo.getPurchaseAmount()


        basketViewModel.locality.observe(viewLifecycleOwner) {
            binding.materialToolbar.title  = it
        }


        Repo.params[2]?.let { binding.materialToolbar.subtitle =it }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}