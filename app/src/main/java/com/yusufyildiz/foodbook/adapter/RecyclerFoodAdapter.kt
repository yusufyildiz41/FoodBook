package com.yusufyildiz.foodbook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.allViews
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yusufyildiz.foodbook.R
import com.yusufyildiz.foodbook.databinding.FoodRecyclerRowBinding
import com.yusufyildiz.foodbook.model.Food
import com.yusufyildiz.foodbook.util.downloadImage
import com.yusufyildiz.foodbook.util.makePlaceHolder
import com.yusufyildiz.foodbook.view.ListOfFoodFragmentDirections
import kotlinx.android.synthetic.main.food_recycler_row.view.*

class RecyclerFoodAdapter(var foodList : ArrayList<Food>) : RecyclerView.Adapter<RecyclerFoodAdapter.foodViewHolder>(),FoodClickListener {

    class foodViewHolder(var view : FoodRecyclerRowBinding) : RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): foodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.food_recycler_row,parent,false)
        val view = DataBindingUtil.inflate<FoodRecyclerRowBinding>(inflater,R.layout.food_recycler_row,parent,false)
        return foodViewHolder(view)
    }

    override fun onBindViewHolder(holder: foodViewHolder, position: Int) {

        holder.view.food = foodList.get(position)
        holder.view.listener = this
        /*
          holder.itemView.foodName.text = foodList.get(position).name
        holder.itemView.calories.text = foodList.get(position).calorie
        //Add Images with glide or picasso
        holder.itemView.imageView.downloadImage(foodList.get(position).image, makePlaceHolder(holder.itemView.context))
        holder.itemView.setOnClickListener{

            var action = ListOfFoodFragmentDirections.actionListOfFoodFragmentToDetailsOfFoodFragment(foodList.get(position).uuid)
            Navigation.findNavController(it).navigate(action)

        }
         */



    }

    override fun getItemCount(): Int {
        return foodList.size
    }

    fun updateFoodList(newFoodList : List<Food>)
    {
        foodList.clear()
        foodList.addAll(newFoodList)
        notifyDataSetChanged()

    }

    override fun foodClicked(view: View) {
        val uuid = view.food_uuid.text.toString().toIntOrNull()

        uuid?.let {
            val action = ListOfFoodFragmentDirections.actionListOfFoodFragmentToDetailsOfFoodFragment(it)
            Navigation.findNavController(view).navigate(action)
        }


    }

}