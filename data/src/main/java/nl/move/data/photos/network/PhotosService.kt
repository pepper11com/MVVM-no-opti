package nl.move.data.photos.network

import io.ktor.client.request.get
import nl.move.data.network.HttpClientProvider
import nl.move.data.photos.network.response.PhotoResponse

class PhotosService(private val httpClientProvider: HttpClientProvider) {

    suspend fun fetchPhotos(): List<PhotoResponse> = httpClientProvider.client.get("photos")
}