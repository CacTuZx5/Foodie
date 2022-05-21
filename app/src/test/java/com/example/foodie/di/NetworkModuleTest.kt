package com.example.foodie.di

import android.util.Log
import com.example.foodie.model.Food
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkModuleTest {
    private var server = MockWebServer()

    fun setUp(): MockWebServer {
        server = MockWebServer()
        val response = MockResponse()
            .setResponseCode(200)
            .setBody("{\"image\":\"https://foodish-api.herokuapp.com/images/pizza/pizza52.jpg\"}")
        server.enqueue(response)

        return server

    }

    @Before
    fun setup() {
        server.start(8080)
        val baseUrl: HttpUrl = server.url("https://foodish-api.herokuapp.com/images/pizza/pizza52.jpg")
    }
    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `300 error`() {
        server.enqueue(MockResponse().setResponseCode(300))

        NetworkModule.api
        Assert.assertEquals("Redirection", NetworkModule.api)
    }

    @Test
    fun `400 error`() {
        server.enqueue(MockResponse().setResponseCode(400))

        Assert.assertEquals("Client Error",  NetworkModule.api.getFoods())
    }

    @Test
    fun `500 error`() {
        server.enqueue(MockResponse().setResponseCode(500))

        Assert.assertEquals("Server Error",  NetworkModule.api)
    }

    @Test
    fun `Successfull Any Food request example`() {
        server.enqueue(MockResponse().setBody("success"))

        NetworkModule.api.getOneFood().enqueue(object : Callback<Food> {
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                if (response.body()!=null){
                    var fooditem: Food = response.body()!!
                    val list = fooditem.image.split("/")
                    val name=list[3]
                    Assert.assertEquals("images", name)
                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                Log.d("Food list fragment error",t.message.toString())

            }
        })
    }

    @Test
    fun `Successfull get Pizza example`() {
        NetworkModule.api.getOneFood().enqueue(object : Callback<Food> {
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                if (response.body()!=null){
                    var fooditem: Food = response.body()!!
                    val list = fooditem.image.split("/")
                    val name=list[4]
                    Assert.assertEquals("https://foodish-api.herokuapp.com/images/pizza/pizza72.jpg", fooditem.image)
                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                Log.d("Food list fragment error",t.message.toString())

            }
        })
    }

    @Test
    fun `Success request image name succesfully parsed example`() {
        server.enqueue(MockResponse().setBody("success"))
        NetworkModule.api.getOneFood().enqueue(object : Callback<Food> {
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                if (response.body()!=null){
                    var fooditem: Food = response.body()!!
                    val list = fooditem.image.split("/")
                    val name=list[4]
                    Assert.assertEquals("pizza", name)

                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                Log.d("Food list fragment error",t.message.toString())
            }
        })
    }

    @Test
    fun `Successfull delete request example`() {
        var fooditem: Food= Food("new food")
        NetworkModule.api.deleteFood(fooditem).enqueue(object : Callback<Food> {
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                if (response.body()!=null){
                    var fooditem: Food = response.body()!!
                    val list = fooditem.image.split("/")
                    val name=list[4]
                    Assert.assertEquals("https://foodish-api.herokuapp.com/images/pizza/pizza52.jpg", fooditem.image)

                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                Log.d("Food list fragment error",t.message.toString())

            }
        })
    }



    @Test
    fun `Fail example`() {

        server.enqueue(MockResponse().setBody("fail"))
        var fooditem: Food= Food("new food")
        NetworkModule.api.createNewFood(fooditem).enqueue(object : Callback<Food> {
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                if (response.body()!=null){
                    Assert.assertEquals("https://foodish-api.herokuapp.com/images/pizza/pizza52.jpg", response)
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Food>, t: Throwable) {

            }
        })
    }


    @Test
    fun getApi() {
    }

    @Test
    fun provideRetrofit() {
    }

    @Test
    fun provideFoodService() {
    }

    @Test
    fun provideImageLoader() {
    }
}