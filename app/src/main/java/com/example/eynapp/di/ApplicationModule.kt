package com.example.andrey.lastfmapp.di

import com.example.andrey.lastfmapp.api.MessagingService
import com.example.andrey.lastfmapp.utils.IScheduler
import com.example.andrey.lastfmapp.utils.SchedulerImpl
import com.example.eynapp.BuildConfig
import com.example.eynapp.api.EynInterceptor
import com.example.eynapp.ui.HomeContract
import com.example.eynapp.ui.HomePresenter
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val LASTFM_API = "https://jsonplaceholder.typicode.com/"
// posts

@Module(includes = [ApplicationModule.Bindings::class])
open class ApplicationModule {

    @Singleton
    @Provides
    open fun provideScheduler(scheduler: SchedulerImpl): IScheduler = scheduler

    @Singleton
    @Provides
    fun provideLastfmService(retrofit: Retrofit): MessagingService {
        return retrofit.create(MessagingService::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient = OkHttpClient().newBuilder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .addInterceptor(EynInterceptor())
            .build()

    @Singleton
    @Provides
    fun provideRetrofitClient(client: OkHttpClient): Retrofit = retrofit2.Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(LASTFM_API)
            .build()

    @Module
    internal interface Bindings {
        @Binds
        fun bindPresenter(impl: HomePresenter): HomeContract.Presenter
    }

    /*
    @Singleton
    @Provides
    open fun provideAlbumOrchestrator(
            interactor: AlbumApiInteractor
    ): IAlbumOrchestrator {
        return AlbumOrchestrator(interactor)
    }

    @Singleton
    @Provides
    fun provideAlbumApiInteractor(
            api: MessagingService,
            detailsResponseMapper : Function<AlbumDetailsResponseDTO, AlbumDetails>,
            searchResponseMapper : Function<AlbumSearchResponseDTO, List<Album>>
    ): AlbumApiInteractor {
        return AlbumApiRetrofitInteractor(api, detailsResponseMapper, searchResponseMapper)
    }

    @Singleton
    @Provides
    fun provideAlbumMapper(
            impl: AlbumMapper
    ): Function<AlbumSearchResponseDTO.AlbumSummaryDTO, Album>  = impl

    @Singleton
    @Provides
    fun provideAlbumSearchResponseMapper(
            impl: AlbumSearchToDomainMapper
    ): Function<AlbumSearchResponseDTO, List<Album>>  = impl

    @Singleton
    @Provides
    fun provideAlbumDetailsResponseMapper(
            impl: AlbumDetailsToDomainMapper
    ): Function<AlbumDetailsResponseDTO, AlbumDetails>  = impl
    */
}