package nl.move.presentation.photolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import nl.move.presentation.util.EventFlow
import nl.move.presentation.util.MutableEventFlow
import nl.move.presentation.util.TypedUIState
import nl.move.presentation.util.launchCatchingOnIO
import nl.move.presentation.util.setError
import nl.move.presentation.util.setLoading
import nl.move.presentation.util.setNormal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nl.move.domain.photos.GetPhotos
import nl.move.domain.photos.model.Photo
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class PhotoListViewModel(private val getPhotos: GetPhotos) : ViewModel() {

    private val _navigation = MutableEventFlow<PhotoListEvent>()
    val navigation: EventFlow<PhotoListEvent> = _navigation.asEventFlow()

    private val _uiState = MutableStateFlow<TypedUIState<List<Photo>, Unit>>(TypedUIState.Loading)
    val uiState: StateFlow<TypedUIState<List<Photo>, Unit>> by lazy {
        fetchPhotos()
        _uiState.asStateFlow()
    }

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    fun onRefresh() = fetchPhotos(true)

    fun onRetryClicked() = fetchPhotos()

    fun onPhotoClicked(photo: Photo) { _navigation.setEvent(PhotoListEvent.PhotoClicked(photo.id)) }

    private fun fetchPhotos(isRefresh: Boolean = false) {
        viewModelScope.launchCatchingOnIO({ onFetchPhotosError(isRefresh) }) {
            if (isRefresh) _isRefreshing.tryEmit(true) else _uiState.setLoading()

            val photosList = getPhotos()
            _uiState.setNormal(photosList)

            if (isRefresh) _isRefreshing.tryEmit(false)
        }
    }

    private fun onFetchPhotosError(isRefresh: Boolean) {
        if (isRefresh) _isRefreshing.tryEmit(false) else _uiState.setError()
    }
}