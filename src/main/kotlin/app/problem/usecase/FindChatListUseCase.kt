package app.problem.usecase

import app.problem.service.ChatService
import app.problem.vo.ChatListRequest
import app.problem.vo.ChatListResponse
import app.problem.vo.ChatThreadSummary
import org.springframework.stereotype.Component

@Component
class FindChatListUseCase(
    private val chatService: ChatService
) : Usecase<ChatListRequest, ChatListResponse> {

    override fun execute(input: ChatListRequest): ChatListResponse {
        val chatPage = chatService.getChatList(input.page, input.size, input.sort)

        return ChatListResponse(
            content = chatPage.content.map {
                ChatThreadSummary(
                    threadId = it.threadId,
                    lastMessageAt = it.updatedAt,
                    firstQuestion = it.firstQuestion
                )
            },
            page = chatPage.number,
            size = chatPage.size,
            totalElements = chatPage.totalElements,
            totalPages = chatPage.totalPages
        )
    }
} 