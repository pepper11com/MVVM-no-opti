package nl.move.domain.photos.data

import nl.move.domain.photos.model.Photo

interface PhotoRepository {

    suspend fun fetchPhotos(): List<Photo>

    suspend fun fetchPhoto(id: Int): Photo
}