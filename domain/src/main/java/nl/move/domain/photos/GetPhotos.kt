package nl.move.domain.photos

import nl.move.domain.photos.data.PhotoRepository
import nl.move.domain.photos.model.Photo

class GetPhotos(private val photoRepository: PhotoRepository) {

    suspend operator fun invoke(): List<Photo> {
        return photoRepository.fetchPhotos()
            .sortedBy { it.title }
    }
}