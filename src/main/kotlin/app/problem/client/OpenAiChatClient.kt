package app.problem.client

import app.problem.entity.ChatMessageEntity
import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.chat.messages.Message
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.messages.AssistantMessage
import org.springframework.ai.openai.OpenAiChatOptions
import org.springframework.stereotype.Component

@Component
class OpenAiChatClient(
    private val chatClient: ChatClient
) {

    fun sendMessage(
        prompt: String,
        chatHistory: List<ChatMessageEntity> = emptyList(),
        model: String? = DEFAULT_MODEL
    ): String = chatClient.prompt()
        .messages(createMessage(prompt, chatHistory))
        .options(OpenAiChatOptions.builder().model(model).build())
        .call()
        .content() ?: "응답을 받을 수 없습니다."

    private fun createMessage(
        prompt: String,
        chatHistory: List<ChatMessageEntity> = emptyList()
    ): List<Message> {
        val messages = mutableListOf<Message>()
        chatHistory.forEach { chatMessage ->
            messages.add(UserMessage(chatMessage.question))
            messages.add(AssistantMessage(chatMessage.answer))
        }

        messages.add(UserMessage(prompt))
        return messages
    }

    companion object {
        const val DEFAULT_MODEL = "gpt-4o-mini"
    }
} 