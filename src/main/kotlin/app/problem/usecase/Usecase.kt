package app.problem.usecase

fun interface Usecase<I, O> {

    fun execute(input: I): O

}