package app.problem.controller

import app.problem.usecase.ChatUseCase
import app.problem.vo.ChatRequest
import app.problem.vo.ChatResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chat")
class ChatController(
    private val chatUseCase: ChatUseCase
) {

    @PostMapping("/ask")
    fun ask(@RequestBody request: ChatRequest): ChatResponse = chatUseCase.execute(request)
}