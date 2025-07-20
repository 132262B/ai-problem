package app.problem.vo

data class ChatResponse(
    val response: String,
    val model: String,
    val threadId: String,
    val timestamp: Long = System.currentTimeMillis()
) 