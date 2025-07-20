package app.problem.service

import app.problem.client.OpenAiChatClient
import app.problem.entity.ChatEntity
import app.problem.vo.ChatRequest
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service

@Service
class OpenAiService(
    private val openAiChatClient: OpenAiChatClient
) {
    
    private val logger = KotlinLogging.logger {}
    
    fun generateResponse(request: ChatRequest, chatHistory: List<ChatEntity>): String {
        logger.info { "OpenAI API 호출 시작: model=${request.model}, hasHistory=${chatHistory.isNotEmpty()}" }
        return openAiChatClient.sendMessage(request.question, chatHistory, request.model)
            .also { response ->
                logger.info { "OpenAI API 응답 받음: responseLength=${response.length}" }
            }
    }
} 