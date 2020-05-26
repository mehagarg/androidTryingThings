package com.example.tinderlikecardstack

import android.app.Application
import com.example.tinderlikecardstack.di.ApplicationComponent
import com.example.tinderlikecardstack.di.ApplicationModule
import com.example.tinderlikecardstack.di.DaggerApplicationComponent

class CardstackApplication : Application() {
    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()

        // Configure Realm for the application
//        val realmConfiguration: RealmConfiguration = Builder(this)
//            .name("cardstack.realm")
//            .build()
//        Realm.deleteRealm(realmConfiguration) // Clean slate
//
//        Realm.setDefaultConfiguration(realmConfiguration) // Make this Realm the default

    }


    fun getComponent(): ApplicationComponent = applicationComponent
}