package app.problem.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "chat")
data class ChatEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false, unique = true)
    val threadId: String,
    
    @Column(nullable = true, length = 1000)
    val firstQuestion: String? = null,
    
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    @Column(nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
) 