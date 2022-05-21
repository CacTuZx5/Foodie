package com.example.foodie

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foodie.databinding.ActivityMainBinding
import com.example.foodie.ui.foodlist.MainActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
//@RunWith(RobolectricTestRunner.class)

public class MainActivityTest {
    var mainActivity: MainActivity? = null
    private lateinit var binding: ActivityMainBinding



    @Before
    @Throws(Exception::class)
    fun setup() {
// Calls the lifecycle: create-start-postCreate-resume
        //mainActivity = Roboelectric.setupActivity(MainActivity::class.java)
    }

    @Test
    @Throws(java.lang.Exception::class)
    fun testAddButtonIsDisplayed() {
        /*Assert.assertEquals(
            binding.fab.visibility,View.VISIBLE
        )*/
    }
    @Test
    fun onCreate() {
    }

    @Test
    fun onCreateOptionsMenu() {
    }

    @Test
    fun onOptionsItemSelected() {
    }

    @Test
    fun onSupportNavigateUp() {
    }
}