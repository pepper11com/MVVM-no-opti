package nl.move.domain.comments.data

import nl.move.domain.comments.model.Comment

interface CommentRepository {

    suspend fun fetchComments(): List<Comment>
}