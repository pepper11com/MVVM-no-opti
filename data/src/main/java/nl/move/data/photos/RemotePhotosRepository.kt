package nl.move.data.photos

import nl.move.data.photos.PhotoMapper.toDomain
import nl.move.data.photos.network.PhotosService
import nl.move.data.photos.storage.PhotoDataStorage
import nl.move.domain.photos.data.PhotoRepository
import nl.move.domain.photos.model.Photo

class RemotePhotosRepository(
    private val photosService: PhotosService,
    private val photosDataStorage: PhotoDataStorage,
) : PhotoRepository {

    override suspend fun fetchPhotos(): List<Photo> {
        val cachedPhotos = photosDataStorage.getPhotosCache()
        return if (cachedPhotos != null && !photosDataStorage.isCacheExpired()) {
            cachedPhotos
        } else {
            fetchCachePhotosFromRemote()
        }
    }

    override suspend fun fetchPhoto(id: Int): Photo {
        val photos = photosDataStorage.getPhotosCache() ?: fetchCachePhotosFromRemote()
        return photos.find { it.id == id } ?: throw Exception()
    }

    private suspend fun fetchCachePhotosFromRemote(): List<Photo> {
        return photosService.fetchPhotos()
            .toDomain()
            .also(photosDataStorage::setPhotoCache)
    }
}