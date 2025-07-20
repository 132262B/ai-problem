package app.problem.service

import app.problem.entity.ChatEntity
import app.problem.entity.ChatMessageEntity
import app.problem.repository.ChatMessageRepository
import app.problem.repository.ChatRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class ChatService(
    private val chatRepository: ChatRepository,
    private val chatMessageRepository: ChatMessageRepository
) {

    fun getChatHistory(requestThreadId: String?, threadId: String): List<ChatMessageEntity> =
        if (requestThreadId != null) {
            chatMessageRepository.findByThreadIdOrderByCreatedAtAsc(threadId)
        } else {
            emptyList()
        }

    fun getChatList(
        page: Int,
        size: Int,
        sort: String,
    ): Page<ChatEntity> {
        val sort = if (sort.lowercase() == "asc") {
            Sort.by(Sort.Direction.ASC, "createdAt")
        } else {
            Sort.by(Sort.Direction.DESC, "createdAt")
        }

        val pageable = PageRequest.of(page, size, sort)
        return chatRepository.findAll(pageable)
    }

    @Transactional
    fun getOrCreateChat(threadId: String, firstQuestion: String? = null): ChatEntity =
        chatRepository.findByThreadId(threadId).orElseGet {
            chatRepository.save(
                ChatEntity(
                    threadId = threadId,
                    firstQuestion = firstQuestion
                )
            )
        }

    @Transactional
    fun saveMessage(chat: ChatEntity, question: String, answer: String): ChatMessageEntity = chatMessageRepository
        .save(
            ChatMessageEntity(
                chat = chat,
                question = question,
                answer = answer
            )
        ).also {
            chatRepository.updateUpdatedAt(chat.id, LocalDateTime.now())
        }

    fun getThreadMessages(threadId: String): List<ChatMessageEntity> = chatMessageRepository
        .findByThreadIdOrderByCreatedAtAsc(threadId)

    @Transactional
    fun deleteThread(threadId: String) {
        chatRepository.findByThreadId(threadId).orElse(null)
            ?: throw IllegalArgumentException("존재하지 않은 threadId입니다.")
        
        chatMessageRepository.deleteByThreadId(threadId)
        chatRepository.deleteByThreadId(threadId)
    }
}