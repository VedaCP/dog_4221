package com.example.dog_4221.model.remote


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogAPI {

    @GET("breeds/list")
    suspend fun fetchBreedList(): Response<WrapperBreed>

    @GET ("breed/{breed}/images/")
    suspend fun fetchImagesByBreed(@Path("breed")breed: String) : Response<WrapperImages>

}