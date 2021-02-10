package com.example.dog_4221.model.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (entities = [BreedEntity::class, ImagesBreed::class], version = 1)
abstract class DogDataBase : RoomDatabase() {

    abstract fun getDogDao() : DogDao

    companion object {
       // @Volatile
        private var INSTANCE : DogDataBase? = null
        fun getDataBase(context: Context): DogDataBase {
          /*  val tempInstance = INSTANCE
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
            }*/
            return INSTANCE ?: synchronized(this){
                val tempInstance = Room.databaseBuilder(context.applicationContext,
                        DogDataBase::class.java,
                        "dog_db")
                        .build()
                INSTANCE = tempInstance
                tempInstance
            }
        }
    }
}

