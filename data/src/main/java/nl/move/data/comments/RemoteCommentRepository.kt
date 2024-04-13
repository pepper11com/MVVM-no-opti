package nl.move.data.comments

import nl.move.data.comments.CommentMapper.toDomain
import nl.move.data.comments.network.CommentService
import nl.move.domain.comments.data.CommentRepository
import nl.move.domain.comments.model.Comment

class RemoteCommentRepository(
    private val commentService: CommentService,
) : CommentRepository {

    override suspend fun fetchComments(): List<Comment> {
        return commentService.fetchComments()
            .toDomain()
    }
}