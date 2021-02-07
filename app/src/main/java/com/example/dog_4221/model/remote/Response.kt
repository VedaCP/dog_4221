package com.example.dog_4221.model.remote

import com.google.gson.annotations.SerializedName

data class BreedResponse(@SerializedName("message") val breed: List<String>,
                         @SerializedName("status") val status: String)

data class ImageResponse(@SerializedName("message") val breed: List<String>,
                         @SerializedName("status") val status: String)