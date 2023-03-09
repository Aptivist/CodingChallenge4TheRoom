package com.aptivist.roomchallengeone.di

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.Volley
import org.koin.core.module.dsl.binds
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {
    singleOf(::provideRequestQueue){
        binds(listOf(RequestQueue::class))
    }

}

fun provideRequestQueue(context: Context) = Volley.newRequestQueue(context)