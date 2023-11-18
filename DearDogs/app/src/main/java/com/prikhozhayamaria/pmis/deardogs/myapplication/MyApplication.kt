package com.prikhozhayamaria.pmis.deardogs.myapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {
    /* override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         startKoin {
             androidContext(this@MyApplication)
             modules()
         }}
         private fun modules() {

         }

         private fun androidContext(applicationDearDogs: MyApplication) {

         }*/

    private fun startKoin(function: () -> Unit) {


    }
}