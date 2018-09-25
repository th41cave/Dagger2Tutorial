package com.example.raevskiyv.daggertutorial.di.component

import com.example.raevskiyv.daggertutorial.api.RandomUsersApi
import com.example.raevskiyv.daggertutorial.di.module.PicassoModule
import com.example.raevskiyv.daggertutorial.di.module.RandomUsersModule
import com.squareup.picasso.Picasso
import dagger.Component

@Component(modules = [RandomUsersModule::class, PicassoModule::class])
interface IRandomUserComponent {
    fun getRandomUserService(): RandomUsersApi
    fun getPicasso(): Picasso
}