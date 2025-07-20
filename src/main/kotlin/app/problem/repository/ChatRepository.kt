package app.problem.repository

import app.problem.entity.ChatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRepository : JpaRepository<ChatEntity, Long> {
    fun findByThreadIdOrderByCreatedAtAsc(threadId: String): List<ChatEntity>
} 