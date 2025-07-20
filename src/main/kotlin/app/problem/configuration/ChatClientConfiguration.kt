package app.problem.configuration

import org.springframework.ai.chat.client.ChatClient
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChatClientConfiguration {
    
    @Bean
    fun chatClient(openAiChatModel: OpenAiChatModel): ChatClient {
        return ChatClient.builder(openAiChatModel).build()
    }
} 