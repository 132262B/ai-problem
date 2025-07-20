package app.problem.controller

import app.problem.usecase.DeleteChatUseCase
import app.problem.usecase.FindChatListUseCase
import app.problem.usecase.CreateChatUseCase
import app.problem.usecase.FindThreadMessagesUseCase
import app.problem.vo.*
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chat")
class ChatController(
    private val createChatUseCase: CreateChatUseCase,
    private val findChatListUseCase: FindChatListUseCase,
    private val findThreadMessagesUseCase: FindThreadMessagesUseCase,
    private val deleteChatUseCase: DeleteChatUseCase
) {

    @PostMapping("/ask")
    fun ask(@RequestBody request: ChatRequest): ChatResponse = createChatUseCase.execute(request)

    @GetMapping
    fun getChatList(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
        @RequestParam(defaultValue = "desc") sort: String
    ): ChatListResponse = findChatListUseCase.execute(ChatListRequest(page, size, sort))

    @GetMapping("/thread/{threadId}")
    fun getThreadMessages(
        @PathVariable threadId: String
    ): ChatThreadMessagesResponse = findThreadMessagesUseCase.execute(threadId)

    @DeleteMapping("/thread/{threadId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteThread(
        @PathVariable threadId: String
    ) = deleteChatUseCase.execute(threadId)
}