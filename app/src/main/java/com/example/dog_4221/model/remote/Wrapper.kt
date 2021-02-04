package com.example.dog_4221.model.remote

import com.google.gson.annotations.SerializedName

data class WrapperBreed (
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: String)

data class WrapperImages (
    @SerializedName("message")
    val message: List<String>,
    @SerializedName("status")
    val status: String)
