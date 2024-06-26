package nl.move.data.comments

import nl.move.data.comments.network.response.CommentResponse
import nl.move.domain.comments.model.Comment

object CommentMapper {

    internal fun List<CommentResponse>.toDomain(): List<Comment> {
        return map { it.toDomain() }
    }

    internal fun CommentResponse.toDomain(): Comment {
        return Comment(
            postId = postId,
            id = id,
            name = name,
            email = email,
            body = body,
        )
    }
}