package com.example.foodie.ui.foodlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.foodie.R
import com.example.foodie.data.FoodDatabase
import com.example.foodie.databinding.ActivityMainBinding
import com.example.foodie.model.Food
import com.google.firebase.analytics.FirebaseAnalytics
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var analytics:FirebaseAnalytics

    private lateinit var foodAdapter:FoodItemAdapter
    private lateinit var mainMvvm01:MainViewModel
    private lateinit var f:Food
    lateinit var foodDatabase:FoodDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytics=FirebaseAnalytics.getInstance(this)

         foodDatabase= FoodDatabase.getInstance(this)

       mainMvvm01= ViewModelProvider(this)[MainViewModel::class.java]

        f= Food("")
        foodAdapter=FoodItemAdapter()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)


        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

       binding.fab.setOnClickListener {
           lifecycleScope.launch {
               addFood()
               throw RuntimeException("Test Crash")
           }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.AboutFragment -> { findNavController(R.id.nav_host_fragment_content_main).navigate(R.id.action_FirstFragment_to_SecondFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }


    private suspend fun addFood() {
        val inflter = LayoutInflater.from(this)
        val v = inflter.inflate(R.layout.add_item,null)
        val foodimage = v.findViewById<EditText>(R.id.fImage)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Ok"){
                dialog,_->
            val fimage = foodimage.text.toString()

            f.image=fimage
            mainMvvm01.createNewFood(f)
            Toast.makeText(this,"Food saved", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel"){
                dialog,_->
            dialog.dismiss()
            Toast.makeText(this,"Cancel", Toast.LENGTH_SHORT).show()

        }
        addDialog.create()
        addDialog.show()
        foodDatabase.foodDao().insertFood(f)
        foodAdapter.notifyDataSetChanged()
    }
}