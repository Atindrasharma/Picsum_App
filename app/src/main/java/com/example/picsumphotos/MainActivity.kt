package com.example.picsumphotos
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: MyAdapter
    lateinit var gridLayoutManger: GridLayoutManager

    lateinit var recyclerActivityViewModel: RecyclerActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview_users.setHasFixedSize(true)
        gridLayoutManger =GridLayoutManager(this,2)
        recyclerview_users.layoutManager=gridLayoutManger

        getMyData()
    }

    private fun getMyData() {

        recyclerActivityViewModel=ViewModelProvider(this).get(RecyclerActivityViewModel::class.java)
        recyclerActivityViewModel.getRecyclerListDataObserver().observe(this,Observer<List<MyData>>{

            myAdapter= MyAdapter(baseContext,it)
             myAdapter.notifyDataSetChanged()
            recyclerview_users.adapter=myAdapter

        })

recyclerActivityViewModel.makeApiCall()

    }
}