package nl.move.presentation.photodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nl.move.domain.comments.GetComments
import nl.move.domain.photos.GetPhoto
import nl.move.presentation.photodetail.model.DetailState
import nl.move.presentation.photodetail.model.DetailViewModelArgs
import nl.move.presentation.util.EventFlow
import nl.move.presentation.util.MutableEventFlow
import nl.move.presentation.util.TypedUIState
import nl.move.presentation.util.launchCatchingOnIO
import nl.move.presentation.util.setError
import nl.move.presentation.util.setLoading
import nl.move.presentation.util.setNormal
import nl.move.presentation.util.updateIfNormal
import org.koin.android.annotation.KoinViewModel
import org.koin.core.annotation.InjectedParam

@KoinViewModel
class DetailViewModel(
    private val getPhoto: GetPhoto,
    private val getComments: GetComments,
    @InjectedParam private val args: DetailViewModelArgs,
) : ViewModel() {

    private val _combinedState = MutableStateFlow<TypedUIState<DetailState, Unit>>(TypedUIState.NormalN)
    val combinedState: StateFlow<TypedUIState<DetailState, Unit>> by lazy {
        fetchPhoto()
        _combinedState.asStateFlow()
    }

    private val _navigation = MutableEventFlow<DetailEvent>()
    val navigation: EventFlow<DetailEvent> = _navigation.asEventFlow()

    fun onRetryClicked() { fetchPhoto() }

    fun onBackClicked() { _navigation.setEvent(DetailEvent.CloseDetails) }

    private fun fetchPhoto() {
        viewModelScope.launchCatchingOnIO(onError = { _combinedState.setError() }) {
            _combinedState.setLoading()
            val photo = getPhoto(args.photoId)
            _combinedState.setNormal(DetailState(photo, TypedUIState.Loading))

            fetchComments()
        }
    }

    private fun fetchComments() {
        viewModelScope.launchCatchingOnIO(onError = { _combinedState.setError() }) {
            _combinedState.updateIfNormal { currentState ->
                currentState.copy(commentsState = TypedUIState.Loading)
            }

            val comments = getComments()

            _combinedState.updateIfNormal { currentState ->
                currentState.copy(commentsState = TypedUIState.Normal(comments))
            }
        }
    }
}