package app.problem.controller

import app.problem.usecase.ChatListUseCase
import app.problem.usecase.ChatUseCase
import app.problem.usecase.ThreadMessagesUseCase
import app.problem.vo.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chat")
class ChatController(
    private val chatUseCase: ChatUseCase,
    private val chatListUseCase: ChatListUseCase,
    private val threadMessagesUseCase: ThreadMessagesUseCase
) {

    @PostMapping("/ask")
    fun ask(@RequestBody request: ChatRequest): ChatResponse = chatUseCase.execute(request)

    @GetMapping
    fun getChatList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(defaultValue = "desc") sort: String
    ): ChatListResponse = chatListUseCase.execute(ChatListRequest(page, size, sort))

    @GetMapping("/thread/{threadId}")
    fun getThreadMessages(
        @PathVariable threadId: String
    ): ChatThreadMessagesResponse = threadMessagesUseCase.execute(threadId)
}