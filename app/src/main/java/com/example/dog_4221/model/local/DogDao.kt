package com.example.dog_4221.model.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.dog_4221.model.remote.DogAPI
import com.example.dog_4221.model.remote.WrapperBreed

@Dao
interface DogDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAllDogDao(list: List<BreedEntity>)

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        suspend fun insertImageDog(list: List<ImagesBreed>)

        @Query("SELECT * FROM breed_table")
        fun getAllDogDaoDB(): LiveData<List<BreedEntity>>

        @Query("SELECT * FROM image_table WHERE status = :id")
        fun getDogById(id: String) : LiveData<List<ImagesBreed>>

        //fav Example (siempre se le el objeto con la id correcta)
        @Update
        suspend fun updateImageBreed(imagesBreed: ImagesBreed)

        //Esta es para tarer todos los favoritos y se reemplaza REPLACE (línea 14) por IGNORE
        @Query("SELECT * FROM image_table WHERE fav = 1")
        fun getAllFavImages(): LiveData<List<ImagesBreed>>

    }