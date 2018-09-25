package com.example.raevskiyv.daggertutorial.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(var context: Context) {
    @Provides
    fun context(): Context {
        return context.applicationContext
    }
}