package app.problem.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "chat")
data class ChatEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @Column(nullable = false)
    val threadId: String,
    
    @Column(nullable = false, columnDefinition = "TEXT")
    val question: String,
    
    @Column(nullable = false, columnDefinition = "TEXT")
    val answer: String,
    
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
) 