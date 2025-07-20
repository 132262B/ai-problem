package app.problem.usecase

import app.problem.service.ChatService
import org.springframework.stereotype.Component

@Component
class DeleteChatUseCase(
    private val chatService: ChatService
) : Usecase<String, Unit> {

    override fun execute(input: String) = chatService.deleteThread(input)
}