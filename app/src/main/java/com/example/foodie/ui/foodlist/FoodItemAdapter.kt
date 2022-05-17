package com.example.foodie.ui.foodlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodie.databinding.FoodItemBinding
import com.example.foodie.model.Foodmodel

class FoodItemAdapter():RecyclerView.Adapter<FoodItemAdapter.FoodViewHolder>() {
    private var foodList=ArrayList<Foodmodel>()
    var onItemClick : ((Foodmodel)->Unit)? =null


    fun setfoodList(foodList: List<Foodmodel>){
        this.foodList=foodList as ArrayList<Foodmodel>
        notifyDataSetChanged()
    }

    inner class FoodViewHolder(val binding: FoodItemBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            FoodItemBinding.inflate(LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        Glide.with(holder.itemView).load(foodList[position].image).into(holder.binding.foodImage)
        val list = foodList[position].image.split("/")
        val name=list[4]
        holder.binding.foodName.text=name

        holder.itemView.setOnClickListener{
            onItemClick!!.invoke(foodList[position])
        }
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}