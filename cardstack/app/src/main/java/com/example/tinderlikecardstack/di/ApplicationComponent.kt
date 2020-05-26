package com.example.tinderlikecardstack.di

import com.example.tinderlikecardstack.CardstackApplication
import com.example.tinderlikecardstack.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(cardstackApplication: CardstackApplication)
    fun inject(mainActivity: MainActivity)
}