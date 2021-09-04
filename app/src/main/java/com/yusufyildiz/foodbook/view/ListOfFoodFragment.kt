package com.yusufyildiz.foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufyildiz.foodbook.adapter.RecyclerFoodAdapter
import com.yusufyildiz.foodbook.databinding.FragmentListOfFoodBinding
import com.yusufyildiz.foodbook.model.Food
import com.yusufyildiz.foodbook.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_list_of_food.*


class ListOfFoodFragment : Fragment() {

    private lateinit var binding : FragmentListOfFoodBinding
    private lateinit var viewModel : FoodListViewModel
    private val recyclerFoodAdapter = RecyclerFoodAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListOfFoodBinding.inflate(inflater,container,false)  // View binding in Fragment
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view,savedInstanceState)

       // viewModel = ViewModelProviders.of(this).get(FoodListViewModel::class.java) this code deprecated
        viewModel = ViewModelProvider(this).get(FoodListViewModel::class.java)
        viewModel.refreshData()

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerFoodAdapter

        swipeRefreshLayout.setOnRefreshListener {

            foodLoading.visibility = View.VISIBLE
            foodErrorMessage.visibility = View.GONE
            recyclerView.visibility = View.GONE
            viewModel.refreshFromInternet()
            swipeRefreshLayout.isRefreshing=false

        }


        observeLiveData()

    }

    fun observeLiveData()
    {
        viewModel.foods.observe(viewLifecycleOwner, Observer { foods ->

            foods?.let {
                println("get")
                recyclerView.visibility = View.VISIBLE
                recyclerFoodAdapter.updateFoodList(it)

            }
        })

        viewModel.foodErrorMessage.observe(viewLifecycleOwner, Observer { error ->

            error?.let {

                if(error)
                {
                    foodErrorMessage.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    foodLoading.visibility = View.GONE

                }
                else
                {

                    foodErrorMessage.visibility = View.GONE



                }

            }

        })

        viewModel.foodLoading.observe(viewLifecycleOwner, Observer { loading ->

            loading?.let {

                if(it)
                {
                    recyclerView.visibility = View.GONE
                    foodErrorMessage.visibility = View.GONE
                    foodLoading.visibility = View.VISIBLE
                }
                else
                {
                    foodLoading.visibility = View.GONE


                }

            }

        })


    }


}