package com.example.picsumphotos
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RecyclerActivityViewModel:ViewModel() {

    lateinit var recyclerListData:MutableLiveData<List<MyData>>

    init {

        recyclerListData= MutableLiveData()
    }

    fun getRecyclerListDataObserver(): MutableLiveData<List<MyData>> {
        return recyclerListData
    }

    fun makeApiCall(){
        val BASE_URL="https://picsum.photos/"
        val retrofitBuilder= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(Apiinterface::class.java)
        val retrofitData=retrofitBuilder.getData()
        retrofitData.enqueue(object : Callback<List<MyData>?> {
           override fun onResponse(call: Call<List<MyData>?>, response: Response<List<MyData>?>) {
               val responceBody=response.body()!!

                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())

                }
            }
            override fun onFailure(call: Call<List<MyData>?>, t: Throwable) {

                Log.i("main","error is found")
            }
        })
    }

}


