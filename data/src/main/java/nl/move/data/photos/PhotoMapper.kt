package nl.move.data.photos

import nl.move.data.photos.network.response.PhotoResponse
import nl.move.domain.photos.model.Photo

object PhotoMapper {

    internal fun List<PhotoResponse>.toDomain(): List<Photo> {
        return map { it.toDomain() }
    }

    fun PhotoResponse.toDomain(): Photo {
        return Photo(
            albumId = albumId,
            id = id,
            title = title,
            url = url,
            thumbnailUrl = thumbnailUrl,
        )
    }
}