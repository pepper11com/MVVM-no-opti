package nl.move.domain.comments

import nl.move.domain.comments.data.CommentRepository
import nl.move.domain.comments.model.Comment

class GetComments(private val commentRepository: CommentRepository) {

    suspend operator fun invoke(): List<Comment> {
        return commentRepository.fetchComments()
            .take(Size)
    }

    companion object {

        private const val Size = 20
    }
}