package com.example.dog_4221

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dog_4221.model.local.BreedEntity
import com.example.dog_4221.model.local.DogDao
import com.example.dog_4221.model.local.ImagesBreed
import java.util.stream.IntStream

@Database (entities = [BreedEntity::class, ImagesBreed::class], version = 1)
abstract class DogDataBase : RoomDatabase() {

    abstract fun getDogDao() : DogDao

    companion object {
        @Volatile
        private var INSTANCE : DogDataBase? = null

        fun getDataBase(context: Context): DogDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                DogDataBase::class.java,
                "DogDB")
                    .build()
                INSTANCE = instance
                return instance

            }
        }
    }
}

