package com.example.dog_4221.model.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.dog_4221.model.local.BreedEntity
import com.example.dog_4221.model.local.DogDao
import com.example.dog_4221.model.local.ImagesBreed

class DogRepository(private val dao: DogDao) {

    private val services = RetrofitDogCliente.retrofitInstance()
    val LiveDataDogDaoDB: LiveData<List<BreedEntity>> = dao.getAllDogDaoDB()

    fun listado(listado: List<String>) : List<BreedEntity> {
        val listBreedEntity = mutableListOf<BreedEntity>()
        listado.map {
            listBreedEntity.add(BreedEntity(it))
        }
        return listBreedEntity
    }

    fun imagesBreed(listImage: List<String>, breed:String
    ) : List<ImagesBreed> {
        val listImagesBreed : MutableList<ImagesBreed> = mutableListOf()

        listImage.map {
            listImagesBreed.add(ImagesBreed(imgURL = it, status = breed))
        }
        return listImagesBreed
    }


    //Función que utiliza las coroutinas para la conexión del servicio
    suspend fun getDogWhitCoroutines() {
        Log.d("REPOSITORY", "UTILIZANDO COROUTINES")
        try {
            val response = RetrofitDogCliente.retrofitInstance().fetchBreedList()
            when (response.isSuccessful) {
                true -> response.body()?.let {
                    //Acá se está insertando en la base de datos
                    dao.insertAllDogDao(listado(it.message))
                }
                false -> Log.d("ERROR", " ${response.code()} : ${response.errorBody()}")
            }
        }

        catch(t: Throwable) {
            Log.e("ERROR COROUTINA", t.message.toString())
        }
    }
    suspend fun imageDog(id: String) {
        try {
            val response = RetrofitDogCliente.retrofitInstance().fetchImagesByBreed(id)
            when (response.isSuccessful){
                true -> response.body()?.let{
                    Log.d("repo", "${it}")
                    dao.insertImageDog(imagesBreed(it.message, id))
                }
                false -> Log.d("ERROR", "${response.code()} : ${response.errorBody()}")
            }
        }
        catch (t: Throwable) {
            Log.e("ERROR COROUTINA", t.message.toString())
        }
    }


   fun getDogById(id: String) : LiveData<List<ImagesBreed>> {
         return dao.getDogById(id)
     }
}