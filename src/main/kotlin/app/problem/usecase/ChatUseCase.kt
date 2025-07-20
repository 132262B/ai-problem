package app.problem.usecase

import app.problem.service.ChatService
import app.problem.service.OpenAiService
import app.problem.vo.ChatRequest
import app.problem.vo.ChatResponse
import org.springframework.stereotype.Component
import java.util.*

@Component
class ChatUseCase(
    private val chatService: ChatService,
    private val openAiService: OpenAiService
) : Usecase<ChatRequest, ChatResponse> {

    override fun execute(input: ChatRequest): ChatResponse {
        val threadId = input.threadId ?: UUID.randomUUID().toString()
        val chat = chatService.getOrCreateChat(threadId, input.question)
        val chatHistory = chatService.getChatHistory(input.threadId, threadId)
        val answer = openAiService.generateResponse(input, chatHistory)
        chatService.saveMessage(chat, input.question, answer)
        
        return ChatResponse(
            response = answer,
            model = input.model,
            threadId = threadId
        )
    }
} 