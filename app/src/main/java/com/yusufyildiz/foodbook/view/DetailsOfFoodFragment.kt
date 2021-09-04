package com.yusufyildiz.foodbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yusufyildiz.foodbook.R
import com.yusufyildiz.foodbook.databinding.FragmentDetailsOfFoodBinding
import com.yusufyildiz.foodbook.util.downloadImage
import com.yusufyildiz.foodbook.util.makePlaceHolder
import com.yusufyildiz.foodbook.viewmodel.DetailsOfFoodViewModel
import kotlinx.android.synthetic.main.fragment_details_of_food.*

class DetailsOfFoodFragment : Fragment() {


    private var myFoodId = 0
    private lateinit var binding : FragmentDetailsOfFoodBinding
    private lateinit var viewModel : DetailsOfFoodViewModel

    private lateinit var dataBinding : FragmentDetailsOfFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details_of_food,container,false)

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {

            myFoodId = DetailsOfFoodFragmentArgs.fromBundle(it).foodId


        }

        viewModel = ViewModelProvider(this).get(DetailsOfFoodViewModel::class.java)
        viewModel.getRoomData(myFoodId)

        observeLiveData()

    }

    fun observeLiveData()
    {
        viewModel.foodLiveData.observe(viewLifecycleOwner, Observer { food ->

            food?.let {

                dataBinding.chosenFood = it

                /*
                binding.foodName.text = food.name
                binding.foodCalories.text = food.calorie
                binding.foodCarbohydrate.text = food.carbohydrate
                binding.foodOil.text = food.oil
                binding.foodProtein.text = food.protein

                context?.let {
                    binding.foodImage.downloadImage(food.image, makePlaceHolder(it))
                }

                */


            }

        })

    }

}