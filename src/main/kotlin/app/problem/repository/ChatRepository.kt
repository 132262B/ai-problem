package app.problem.repository

import app.problem.entity.ChatEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface ChatRepository : JpaRepository<ChatEntity, Long> {

    fun findByThreadId(threadId: String): Optional<ChatEntity>
    
    @Modifying
    @Query("UPDATE ChatEntity c SET c.updatedAt = :updatedAt WHERE c.id = :id")
    fun updateUpdatedAt(id: Long, updatedAt: LocalDateTime)
} 