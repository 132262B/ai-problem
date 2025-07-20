package app.problem.vo

data class ChatListRequest(
    val page: Int = 0,
    val size: Int = 10,
    val sort: String = "desc" // asc or desc
) 