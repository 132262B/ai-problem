package app.problem.vo

data class ChatRequest(
    val question: String,
    val model: String = "gpt-4o-mini",
    val threadId: String? = null
) 