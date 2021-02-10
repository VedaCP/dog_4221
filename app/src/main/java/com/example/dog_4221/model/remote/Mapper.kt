package com.example.dog_4221.model.remote

import com.example.dog_4221.model.local.BreedEntity
import com.example.dog_4221.model.local.ImagesBreed

fun fromRemoteToEntityBreed(wrapperBreed: WrapperBreed): List<BreedEntity>{
    return wrapperBreed?.message.map { BreedEntity(it) }
}


/*fun fromRemoteToEntityImage(images: WrapperImages, breed: String): List<ImagesBreed>{
    return  image?.message.map {ImagesBreed((imgURL = it, breed = breed, fav = false)    }
}*/
