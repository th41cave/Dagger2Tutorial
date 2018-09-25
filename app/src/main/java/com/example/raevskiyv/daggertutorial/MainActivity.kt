package com.example.raevskiyv.daggertutorial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.raevskiyv.daggertutorial.adapter.RandomUserAdapter
import com.example.raevskiyv.daggertutorial.api.RandomUsersApi
import com.example.raevskiyv.daggertutorial.di.component.DaggerIRandomUserComponent
import com.example.raevskiyv.daggertutorial.di.module.ContextModule
import com.example.raevskiyv.daggertutorial.model.RandomUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RandomUserAdapter
    private lateinit var randomUsersApi: RandomUsersApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        afterDagger()
    }

    override fun onResume() {
        super.onResume()
        populateUsers()
    }

    private fun afterDagger() {
        val component = DaggerIRandomUserComponent.builder()
                .contextModule(ContextModule(this))
                .build()
        randomUsersApi = component.getRandomUserService()
    }

    private fun initViews() {
        adapter = RandomUserAdapter()
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun populateUsers() {
        val randomUsersCall = randomUsersApi.getRandomUsers(10)
        randomUsersCall.enqueue(object : Callback<RandomUsers> {
            override fun onResponse(call: Call<RandomUsers>, response: Response<RandomUsers>) {
                if (response.isSuccessful) {
                    adapter.setItems(response.body()?.results ?: listOf())
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<RandomUsers>, t: Throwable) {
                Timber.i(t)
            }
        })
    }
}
