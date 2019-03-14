package com.example.andrey.lastfmapp.di

import com.example.andrey.lastfmapp.api.MessagingService
import com.example.andrey.lastfmapp.utils.IScheduler
import com.example.eynapp.ui.HomeActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(activity: HomeActivity)

    // abstract fun inject(activity: AlbumListActivity)


    fun messagingService(): MessagingService

    // fun albumOrchestrator(): IAlbumOrchestrator

    fun scheduler(): IScheduler

    /*
    interface ApplicationComponentProvider {
        val appComponent: ApplicationComponent
    }
    */
}