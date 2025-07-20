package app.problem.usecase

import app.problem.service.ChatService
import app.problem.vo.ThreadMessage
import app.problem.vo.ChatThreadMessagesResponse
import org.springframework.stereotype.Component

@Component
class ThreadMessagesUseCase(
    private val chatService: ChatService
) : Usecase<String, ChatThreadMessagesResponse> {

    override fun execute(input: String): ChatThreadMessagesResponse {
        val messages = chatService.getThreadMessages(input)
        val threadMessages = messages.map { message ->
            ThreadMessage(
                id = message.id,
                question = message.question,
                answer = message.answer,
                createdAt = message.createdAt
            )
        }
        return ChatThreadMessagesResponse(
            threadId = input,
            messages = threadMessages
        )
    }
} 