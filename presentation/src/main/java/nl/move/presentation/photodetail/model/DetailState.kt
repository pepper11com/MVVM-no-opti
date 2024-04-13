package nl.move.presentation.photodetail.model

import nl.move.domain.comments.model.Comment
import nl.move.domain.photos.model.Photo
import nl.move.presentation.util.TypedUIState

data class DetailState(
    val photo: Photo,
    val commentsState: TypedUIState<List<Comment>, Unit>,
)