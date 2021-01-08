package br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist

import br.com.nerdrapido.chucknorrisjokeapp.domain.model.EntityWrapper
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import br.com.nerdrapido.chucknorrisjokeapp.domain.usecase.RandomJokesUseCase
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.mapper.JokePresentationMapper
import kotlinx.coroutines.delay
import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 07/01/2021
 */
class JokeViewModelMock(
    jokesUseCase: RandomJokesUseCase,
    jokePresentationMapper: JokePresentationMapper
) : JokeViewModel(jokesUseCase, jokePresentationMapper) {


    class Builder {

        private val id: String = "fqmCw9YhQO6ln9g2IYs2UQ"
        private val categories: List<String> = emptyList()
        private val createdAtTimestamp = 1578232581843L
        private val iconUrl: String = "https://assets.chucknorris.host/img/avatar/chuck-norris.png"
        private val updatedAtTimestamp = 1578318981843L
        private val url: String = "https://api.chucknorris.io/jokes/fqmCw9YhQO6ln9g2IYs2UQ"
        private val value: String =
            "Chuck Norris got mad at some vegetables, the end results were Black-eyed peas."

        private val jokesDomain = listOf(
            JokeDomain(
                id,
                categories,
                Date(createdAtTimestamp),
                iconUrl,
                Date(updatedAtTimestamp),
                url,
                value
            )
        )

        private var delayMillis = 0L

        private lateinit var returnEntityWrapper: EntityWrapper<List<JokeDomain>>

        private val jokesUseCase = object : RandomJokesUseCase {
            override suspend fun execute(input: RandomJokesUseCase.Input?): EntityWrapper<List<JokeDomain>> {
                delay(delayMillis)
                return returnEntityWrapper
            }
        }

        private val jokeViewModelMock = JokeViewModelMock(jokesUseCase, JokePresentationMapper())

        fun addNetworkError(exception: Exception): Builder {
            returnEntityWrapper = EntityWrapper.NetworkError(exception)
            return this
        }

        fun addGenericError(exception: Exception): Builder {
            returnEntityWrapper = EntityWrapper.GenericError(exception)
            return this
        }

        fun addSuccess(jokes: List<JokeDomain> = jokesDomain): Builder {
            returnEntityWrapper = EntityWrapper.Success(jokes)
            return this
        }

        fun addDelay(delay: Long = 200L): Builder {
            this.delayMillis = delay
            return this
        }

        fun build() = jokeViewModelMock

    }
}
