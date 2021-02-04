package com.example.dog_4221.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breed table")

data class BreedEntity(@PrimaryKey val breed: String)
