package com.example.foodie.ui.foodlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodie.databinding.FragmentHomeBinding
import com.example.foodie.model.Foodmodel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var mainMvvm01:MainViewModel
    private lateinit var foodAdapter:FoodItemAdapter

    private var myfoodList=ArrayList<Foodmodel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        mainMvvm01=ViewModelProvider(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)


        //mainMvvm=ViewModelProviders.of
    }
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding= FragmentHomeBinding.inflate(inflater,container,false)
        //_binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareFoodListRecyclerView()
        for (i in 1..10){
            mainMvvm01.getRandomFood()
        }

        observerrandomFood()
        prepareFoodListRecyclerView()
        onFoodItemClick()


    }

    private fun onFoodItemClick() {
        foodAdapter.onItemClick={food -> myfoodList.remove(food)
            foodAdapter.notifyDataSetChanged()
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
        mainMvvm01.observefoodLiveData().observe(viewLifecycleOwner,object : Observer<Foodmodel>{
            override fun onChanged(t: Foodmodel?) {
                myfoodList.add(t!!)
                //Glide.with(this@FirstFragment).load(t!!.image).into(binding.imgMeal)
                foodAdapter.setfoodList(myfoodList)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }


    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }*/

}