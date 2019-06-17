package com.opiumfive.livetypingdemo

import android.app.Application
import android.content.SharedPreferences
import com.google.gson.GsonBuilder
import com.opiumfive.livetypingdemo.api.IApi
import com.opiumfive.livetypingdemo.feature.list.MealsRepo
import com.opiumfive.livetypingdemo.feature.list.MealsViewModel
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {

    // DI
    val appModule: Module = module {

        viewModel { MealsViewModel(get()) }

        single { MealsRepo(get()) }

        single<IApi> {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
                .create(IApi::class.java)
        }

        factory { GsonBuilder().create() }

        factory {
            OkHttpClient().newBuilder()
                .addInterceptor(ChuckInterceptor(androidContext()))
                .connectTimeout(TIMEOUT_REQUEST, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT_REQUEST, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build()
        }

        factory<SharedPreferences> { androidContext().getSharedPreferences("prefs", 0) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}