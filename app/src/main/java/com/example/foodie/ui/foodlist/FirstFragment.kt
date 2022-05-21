package com.example.foodie.ui.foodlist

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodie.databinding.FragmentHomeBinding
import com.example.foodie.model.Food


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    var activity: Activity? = getActivity()
    //var activity2 = context as Activity


    private lateinit var binding: FragmentHomeBinding
    private lateinit var mainMvvm01:MainViewModel
    private lateinit var foodAdapter:FoodItemAdapter
    private var myfoodList=ArrayList<Food>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainMvvm01= ViewModelProvider(this)[MainViewModel::class.java]

    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareFoodListRecyclerView()
        for (i in 1..10){
            mainMvvm01.getRandomFood()
        }
        mainMvvm01.getOneFood()


        observerrandomFood()
        prepareFoodListRecyclerView()
        onFoodItemClick()
    }

    private fun onFoodItemClick() {
        foodAdapter.onItemClick={food -> myfoodList.remove(food)
            foodAdapter.notifyDataSetChanged()
            mainMvvm01.deleteFood(food)
            }

    }


    private fun prepareFoodListRecyclerView() {
        foodAdapter=FoodItemAdapter()
        binding.recViewFoods.apply {
            layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter=foodAdapter
        }
    }

    private fun observerrandomFood() {
        mainMvvm01.observefoodLiveData().observe(viewLifecycleOwner,object : Observer<Food>{
            override fun onChanged(t: Food?) {
                myfoodList.add(t!!)
                foodAdapter.setfoodList(myfoodList)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}