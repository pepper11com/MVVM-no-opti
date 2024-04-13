package nl.move.domain.photos

import nl.move.domain.photos.data.PhotoRepository
import nl.move.domain.photos.model.Photo

class GetPhoto(private val photoRepository: PhotoRepository) {

    suspend operator fun invoke(id: Int): Photo {
        return photoRepository.fetchPhoto(id)
    }
}