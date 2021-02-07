package com.example.dog_4221.model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.dog_4221.DogDataBase.Companion.getDataBase
import com.example.dog_4221.model.local.BreedEntity
import com.example.dog_4221.model.local.ImagesBreed
import com.example.dog_4221.model.remote.DogRepository
import kotlinx.coroutines.launch

class DogViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DogRepository
    val breedEntityLiveDataFromDB: LiveData<List<BreedEntity>>

    init {
        val dao = getDataBase(application).getDogDao()
        repository = DogRepository(dao)
        viewModelScope.launch {
            repository.getDogWhitCoroutines()
        }
        breedEntityLiveDataFromDB = repository.LiveDataDogDaoDB

    }
    //Actualización de datos de internet
    fun getFetchDogWhitCoroutines() = viewModelScope.launch {
        repository.getDogWhitCoroutines()
    }
    fun getDogById(id: String): LiveData<List<ImagesBreed>> {
        return repository.getDogById(id)

    }
}
