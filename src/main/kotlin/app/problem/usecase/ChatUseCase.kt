package app.problem.usecase

import app.problem.service.ChatService
import app.problem.service.OpenAiService
import app.problem.vo.ChatRequest
import app.problem.vo.ChatResponse
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import java.util.*

@Component
class ChatUseCase(
    private val chatService: ChatService,
    private val openAiService: OpenAiService
) : Usecase<ChatRequest, ChatResponse> {

    private val logger = KotlinLogging.logger {}

    override fun execute(input: ChatRequest): ChatResponse {
        logger.info { "채팅 요청 시작: threadId=${input.threadId}, model=${input.model}, question=${input.question}" }

        val threadId = generateOrUseThreadId(input.threadId)

        return try {
            val chatHistory = chatService.getChatHistory(input.threadId, threadId)
            val aiResponse = openAiService.generateResponse(input, chatHistory)
            chatService.saveChat(threadId, input.question, aiResponse)

            val response = ChatResponse(
                response = aiResponse,
                model = input.model,
                threadId = threadId
            )

            logger.info { "채팅 요청 완료: threadId=$threadId, responseLength=${aiResponse.length}" }
            response

        } catch (e: Exception) {
            logger.error(e) { "채팅 처리 중 오류 발생: threadId=$threadId, error=${e.message}" }
            ChatResponse(
                response = "오류가 발생했습니다: ${e.message}",
                model = input.model,
                threadId = threadId
            )
        }
    }

    private fun generateOrUseThreadId(requestThreadId: String?): String {
        return requestThreadId ?: UUID.randomUUID().toString().also { newThreadId ->
            logger.info { "새 스레드 생성: $newThreadId" }
        }
    }


} 