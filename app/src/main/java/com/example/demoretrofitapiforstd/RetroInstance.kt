package com.example.demoretrofitapiforstd

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// ติดต่อกับ services
class RetroInstance {
    companion object {
        // var baseUrl="https://gorest.co.in/"
        var baseUrl = "\n" +
                "https://gorest.co.in/public/v2/"

        fun getRetroInstance(): Retrofit {

            var logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)
            var client = OkHttpClient.Builder()
            client.addInterceptor(logging)

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}