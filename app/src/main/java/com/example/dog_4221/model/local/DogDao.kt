package com.example.dog_4221.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dog_4221.model.remote.DogAPI
import com.example.dog_4221.model.remote.WrapperBreed

@Dao
interface DogDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAllDogDao(list: List<BreedEntity>)

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertImageDog(list: List<ImagesBreed>)

        @Query("SELECT * FROM breed_table")
        fun getAllDogDaoDB(): LiveData<List<BreedEntity>>

        @Query("SELECT * FROM image_table WHERE status = :id")
        fun getDogById(id: String) : LiveData<List<ImagesBreed>>

    }