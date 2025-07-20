package app.problem.service

import app.problem.entity.ChatEntity
import app.problem.repository.ChatRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatService(
    private val chatRepository: ChatRepository
) {
    
    private val logger = KotlinLogging.logger {}
    
    @Transactional(readOnly = true)
    fun getChatHistory(requestThreadId: String?, threadId: String): List<ChatEntity> {
        return if (requestThreadId != null) {
            chatRepository.findByThreadIdOrderByCreatedAtAsc(threadId).also { history ->
                logger.info { "기존 대화 히스토리 조회: threadId=$threadId, count=${history.size}" }
            }
        } else {
            logger.info { "새 스레드로 대화 히스토리 없음: threadId=$threadId" }
            emptyList()
        }
    }
    
    @Transactional
    fun saveChat(threadId: String, question: String, answer: String): ChatEntity {
        val chat = ChatEntity(
            threadId = threadId,
            question = question,
            answer = answer
        )
        
        return chatRepository.save(chat).also { savedChat ->
            logger.info { "대화 저장 완료: id=${savedChat.id}, threadId=$threadId" }
        }
    }
} 