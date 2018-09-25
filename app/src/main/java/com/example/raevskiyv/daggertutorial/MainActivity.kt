package com.example.raevskiyv.daggertutorial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.raevskiyv.daggertutorial.adapter.RandomUserAdapter
import com.example.raevskiyv.daggertutorial.model.RandomUsers
import com.google.gson.GsonBuilder
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.File
import com.example.raevskiyv.daggertutorial.interfaces.RandomUsersApi



class MainActivity : AppCompatActivity() {

    lateinit var retrofit: Retrofit
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RandomUserAdapter
    lateinit var picasso: Picasso


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        beforeDagger2()
    }

    private fun beforeDagger2() {
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()

        Timber.plant(Timber.DebugTree())

        val cacheFile = File(this.cacheDir, "HttpCache")
        cacheFile.mkdirs()

        val cache = Cache(cacheFile, (10 * 1000 * 1000).toLong()) //10 MB

        val httpLoggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.i(message) })

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        val okHttpClient = OkHttpClient()
                .newBuilder()
                .cache(cache)
                .addInterceptor(httpLoggingInterceptor)
                .build()

        val okHttpDownloader = OkHttp3Downloader(okHttpClient)

        picasso = Picasso.Builder(this).downloader(okHttpDownloader).build()

        retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://randomuser.me/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        populateUsers()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun populateUsers() {
        val randomUsersCall = getRandomUserService().getRandomUsers(10)
        randomUsersCall.enqueue(object : Callback<RandomUsers> {
            override fun onResponse(call: Call<RandomUsers>, response: Response<RandomUsers>) {
                if (response.isSuccessful) {
                    adapter = RandomUserAdapter()
                    adapter.setItems(response.body()?.results ?: listOf())
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<RandomUsers>, t: Throwable) {
                Timber.i(t.message)
            }
        })
    }

    private fun getRandomUserService(): RandomUsersApi {
        return retrofit.create(RandomUsersApi::class.java)
    }
}
