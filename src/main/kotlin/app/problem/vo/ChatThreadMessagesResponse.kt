package app.problem.vo

import java.time.LocalDateTime

data class ChatThreadMessagesResponse(
    val threadId: String,
    val messages: List<ThreadMessage>
)

data class ThreadMessage(
    val id: Long,
    val question: String,
    val answer: String,
    val createdAt: LocalDateTime
) 