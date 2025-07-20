package app.problem.repository

import app.problem.entity.ChatMessageEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageRepository : JpaRepository<ChatMessageEntity, Long> {

    @Query("SELECT cm FROM ChatMessageEntity cm WHERE cm.chat.threadId = :threadId ORDER BY cm.createdAt ASC")
    fun findByThreadIdOrderByCreatedAtAsc(threadId: String): List<ChatMessageEntity>

} 