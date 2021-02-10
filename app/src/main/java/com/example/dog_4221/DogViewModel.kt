package com.example.dog_4221

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.dog_4221.model.local.DogDataBase
import com.example.dog_4221.model.local.BreedEntity
import com.example.dog_4221.model.local.ImagesBreed
import com.example.dog_4221.model.remote.DogRepository
import kotlinx.coroutines.launch

class DogViewModel(application: Application): AndroidViewModel(application) {

    private val repository: DogRepository
   val breedEntityLiveDataFromDB: LiveData<List<BreedEntity>>

    init {
        val dao = DogDataBase.getDataBase(application).getDogDao()
        repository = DogRepository(dao)
        viewModelScope.launch {
            repository.getDogWhitCoroutines()
        }
        breedEntityLiveDataFromDB = repository.listBreed

    }
    //Actualización de datos de internet
    fun getFetchDogWhitCoroutines() = viewModelScope.launch {
        repository.getDogWhitCoroutines()
    }
    fun getAllFavList(): LiveData<List<ImagesBreed>> = repository.listFavImages //This return all
            //fav from database

    fun updateFavImages(imagesBreed: ImagesBreed) = viewModelScope.launch {
        repository.updateFavImages(imagesBreed)

    }

    fun getFetchBreedWhitCoroutines(id: String) = viewModelScope.launch {
        repository.imageDog(id)
    }
    fun getDogById(id: String): LiveData<List<ImagesBreed>> {
      //  getFetchBreedWhitCoroutines(id)
        return repository.getDogById(id)

    }
}
