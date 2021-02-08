package com.example.dog_4221.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "image_table")
data class ImagesBreed(@PrimaryKey val imgURL: String, val status: String)
