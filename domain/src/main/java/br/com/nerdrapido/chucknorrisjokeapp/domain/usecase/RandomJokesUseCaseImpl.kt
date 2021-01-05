package br.com.nerdrapido.chucknorrisjokeapp.domain.usecase

import br.com.nerdrapido.chucknorrisjokeapp.domain.model.EntityWrapper
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import br.com.nerdrapido.chucknorrisjokeapp.domain.repository.JokeRepository

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class RandomJokesUseCaseImpl(
    private val jokeRepository: JokeRepository
) : RandomJokesUseCase {

    override suspend fun execute(input: RandomJokesUseCase.Input?): EntityWrapper<List<JokeDomain>> {
        return safeRepositoryCall {
            val quantity = input?.quantity ?: 1
            jokeRepository.getRandomJokes(quantity)
        }
    }
}
