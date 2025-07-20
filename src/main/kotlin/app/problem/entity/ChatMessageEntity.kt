package app.problem.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "chat_message")
data class ChatMessageEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", nullable = false)
    val chat: ChatEntity,
    
    @Column(nullable = false, columnDefinition = "TEXT")
    val question: String,
    
    @Column(nullable = false, columnDefinition = "TEXT")
    val answer: String,
    
    @Column(nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()
) 