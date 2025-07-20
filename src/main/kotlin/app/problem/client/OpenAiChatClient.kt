package app.problem.client

import app.problem.entity.ChatEntity
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
        chatHistory: List<ChatEntity> = emptyList(),
        model: String? = DEFAULT_MODEL
    ): String = chatClient.prompt()
        .messages(createMessage(prompt, chatHistory))
        .options(OpenAiChatOptions.builder().model(model).build())
        .call()
        .content() ?: "응답을 받을 수 없습니다."

    private fun createMessage(
        prompt: String,
        chatHistory: List<ChatEntity> = emptyList()
    ): List<Message> {
        val messages = mutableListOf<Message>()
        chatHistory.forEach { chat ->
            messages.add(UserMessage(chat.question))
            messages.add(AssistantMessage(chat.answer))
        }

        messages.add(UserMessage(prompt))
        return messages
    }

    companion object {
        const val DEFAULT_MODEL = "gpt-4o-mini"
    }
} 