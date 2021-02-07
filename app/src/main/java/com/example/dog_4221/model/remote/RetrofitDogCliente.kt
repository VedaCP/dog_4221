package com.example.dog_4221.model.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitDogCliente {
    companion object {
        private const val URL_BASE = "https://dog.ceo/api/"
        fun retrofitInstance(): DogAPI {
            val retrofit = Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(DogAPI::class.java)
        }
    }
}