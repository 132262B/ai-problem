package app.problem.service

import app.problem.client.OpenAiChatClient
import app.problem.entity.ChatMessageEntity
import app.problem.vo.ChatRequest
import org.springframework.stereotype.Service

@Service
class OpenAiService(
    private val openAiChatClient: OpenAiChatClient
) {
    
    fun generateResponse(request: ChatRequest, chatHistory: List<ChatMessageEntity>): String {
        return openAiChatClient.sendMessage(request.question, chatHistory, request.model)
    }
} 