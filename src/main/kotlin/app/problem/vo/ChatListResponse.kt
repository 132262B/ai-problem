package app.problem.vo

import java.time.LocalDateTime

data class ChatListResponse(
    val content: List<ChatThreadSummary>,
    val page: Int,
    val size: Int,
    val totalElements: Long,
    val totalPages: Int
)

data class ChatThreadSummary(
    val threadId: String,
    val lastMessageAt: LocalDateTime,
    val firstQuestion: String?
)