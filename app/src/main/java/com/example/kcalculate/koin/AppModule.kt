package com.example.kcalculate.koin

import android.app.Application
import android.content.Context
import com.example.kcalculate.BuildConfig.DEBUG
import com.example.kcalculate.R
import com.example.kcalculate.data.daos.FoodDao
import com.example.kcalculate.data.FoodDataBase
import com.example.kcalculate.data.daos.IngredientsDao
import com.example.kcalculate.network.ApiService
import com.example.kcalculate.repository.IngredientsRepository
import com.example.kcalculate.repository.implementation.IngredientsRepositoryImpl
import com.example.kcalculate.viewmodel.IngredientsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {

    fun provideDatabase(application: Application): FoodDataBase? {
        return FoodDataBase.getDatabase(application.applicationContext)
    }

    fun provideCountriesDao(database: FoodDataBase): FoodDao {
        return  database.foodDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}


val apiModule = module {

    fun provideApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
    single { provideApi(get()) }

}

val networkModule = module {
    val connectTimeout : Long = 40// 20s
    val readTimeout : Long  = 40 // 20s

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
            .readTimeout(readTimeout, TimeUnit.SECONDS)
        if (DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
        }
        okHttpClientBuilder.build()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(client: OkHttpClient, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideHttpClient() }
    single {
        val baseUrl = androidContext().getString(R.string.BASE_URL)
        provideRetrofit(get(), baseUrl)
    }
}

val repositoryModule = module {

    fun provideIngredientsRepository(api: ApiService, dao : IngredientsDao): IngredientsRepository {
        return IngredientsRepositoryImpl(api, dao)
    }
    single { provideIngredientsRepository(get(), get()) }

}


val viewModelModule = module {

    // Specific viewModel pattern to tell Koin how to build CountriesViewModel
    viewModel {
        IngredientsViewModel(repository = get())
    }

}