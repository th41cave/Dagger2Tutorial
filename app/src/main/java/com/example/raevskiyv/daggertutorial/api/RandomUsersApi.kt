package com.example.raevskiyv.daggertutorial.api

import com.example.raevskiyv.daggertutorial.model.RandomUsers

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Hari on 20/11/17.
 */

interface RandomUsersApi {
    @GET("api/1.0/")
    fun getRandomUsers(@Query("results") size: Int): Call<RandomUsers>
}