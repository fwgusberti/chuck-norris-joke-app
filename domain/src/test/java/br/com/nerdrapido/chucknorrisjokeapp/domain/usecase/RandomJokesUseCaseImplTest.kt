package br.com.nerdrapido.chucknorrisjokeapp.domain.usecase

import br.com.nerdrapido.chucknorrisjokeapp.domain.model.EntityWrapper
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import br.com.nerdrapido.chucknorrisjokeapp.domain.repository.JokeRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.*
import java.io.IOException
import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class RandomJokesUseCaseImplTest {

    private val id: String = "12345asdasd"
    private val categories: List<String> = emptyList()
    private val createdAtTimestamp = 1578232581843L
    private val iconUrl: String = "oidhgfoirdsg"
    private val updatedAtTimestamp = 1578318981843L
    private val url: String = "lzsidjflçdjf"
    private val value: String = "lzsidjflçdjf asdasd asdasd"

    private val jokeDomain = JokeDomain(
        id,
        categories,
        Date(createdAtTimestamp),
        iconUrl,
        Date(updatedAtTimestamp),
        url,
        value
    )

    @Test
    fun `test execute RandomJokesUseCase success`() = runBlocking {

        val quantityOfJokes = 2
        val jokeRepository = mock(JokeRepository::class.java)
        doReturn(
            listOf(
                jokeDomain,
                jokeDomain
            )
        ).`when`(jokeRepository).getRandomJokes(quantityOfJokes)


        val useCase = RandomJokesUseCaseImpl(jokeRepository)
        val input = RandomJokesUseCase.Input(quantityOfJokes)
        when (val output = useCase.execute(input)) {
            is EntityWrapper.Success<List<JokeDomain>> -> {
                val jokes = output.value
                Assert.assertEquals(quantityOfJokes , jokes.size)
                Assert.assertEquals(id, jokes[0].id)
                Assert.assertEquals(categories, jokes[0].categories)
                Assert.assertEquals(createdAtTimestamp, jokes[0].createdAt.time)
                Assert.assertEquals(iconUrl, jokes[0].iconUrl)
                Assert.assertEquals(updatedAtTimestamp, jokes[0].updatedAt.time)
                Assert.assertEquals(url, jokes[0].url)
                Assert.assertEquals(value, jokes[0].value)
            }
            else -> throw RuntimeException()
        }
    }

    @Test
    fun `test execute RandomJokesUseCase network error`() = runBlocking {
        val quantityOfJokes = 0
        val jokeRepository = mock(JokeRepository::class.java)
        given(jokeRepository.getRandomJokes(quantityOfJokes)).willAnswer {
            throw IOException("Ooops")
        }
        val useCase = RandomJokesUseCaseImpl(jokeRepository)
        val input = RandomJokesUseCase.Input(quantityOfJokes)
        when (val output = useCase.execute(input)) {
            is EntityWrapper.NetworkError -> Assert.assertNotNull(output.error)
            else -> throw RuntimeException()
        }
    }

    @Test
    fun `test execute RandomJokesUseCase generic error`() = runBlocking {
        val jokeRepository = mock(JokeRepository::class.java)
        given(jokeRepository.getRandomJokes(1)).willAnswer {
            throw RuntimeException("Ooops")
        }
        val useCase = RandomJokesUseCaseImpl(jokeRepository)
        val input = RandomJokesUseCase.Input()
        when (val output = useCase.execute(input)) {
            is EntityWrapper.GenericError -> Assert.assertNotNull(output.error)
            else -> throw RuntimeException()
        }
    }

    @Test
    fun `test if execute with null Input returns 1 joke`() = runBlocking {
        val jokeRepository = mock(JokeRepository::class.java)
        doReturn(
            listOf(
                jokeDomain
            )
        ).`when`(jokeRepository).getRandomJokes(1)
        val useCase = RandomJokesUseCaseImpl(jokeRepository)
        when (val output = useCase.execute(null)) {
            is EntityWrapper.Success<List<JokeDomain>> -> {
                val jokes = output.value
                Assert.assertEquals(1, jokes.size)
            }
            else -> throw RuntimeException()
        }
    }
}
