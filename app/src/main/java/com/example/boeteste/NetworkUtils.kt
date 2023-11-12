package com.example.boeteste

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkUtils {
    companion object {
        val baseUrl = "https://boeapp.onrender.com/"
        fun getRetrofitInstance(): Retrofit {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val gson = GsonBuilder().create()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getApiService(): ApiService {
            return getRetrofitInstance().create(ApiService::class.java)
        }
    }
}