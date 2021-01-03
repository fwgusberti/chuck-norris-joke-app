package br.com.nerdrapido.chucknorrisjokeapp.domain.usecase

import br.com.nerdrapido.chucknorrisjokeapp.domain.model.EntityWrapper
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import br.com.nerdrapido.chucknorrisjokeapp.domain.repository.JokeRepository

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class RandomJokesUseCase(
    private val jokeRepository: JokeRepository
) : UseCase<RandomJokesUseCase.Input, EntityWrapper<List<JokeDomain>>> {

    override suspend fun execute(input: Input): EntityWrapper<List<JokeDomain>> {
        return safeRepositoryCall {
            jokeRepository.getRandomJokes(input.quantity)
        }
    }

    data class Input(
        val quantity: Int = 1
    ) : UseCaseInput
}