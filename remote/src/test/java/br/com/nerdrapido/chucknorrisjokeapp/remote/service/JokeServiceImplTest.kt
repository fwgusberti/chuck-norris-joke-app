package br.com.nerdrapido.chucknorrisjokeapp.remote.service

import br.com.nerdrapido.chucknorrisjokeapp.remote.mapper.JokeMapperRemote
import br.com.nerdrapido.chucknorrisjokeapp.remote.model.JokeRemote
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class JokeServiceImplTest {

    private val id: String = "12345asdasd"
    private val categories: List<String> = emptyList()
    private val createdAt: String = "2020-01-05 13:42:20.841843"
    private val createdAtTimestamp = 1578232581843L
    private val iconUrl: String = "oidhgfoirdsg"
    private val updatedAt: String = "2020-01-06 13:42:20.841843"
    private val updatedAtTimestamp = 1578318981843L
    private val url: String = "lzsidjflçdjf"
    private val value: String = "lzsidjflçdjf asdasd asdasd"

    @Test
    fun `test getRandomJokes JokeServiceImpl`() = runBlocking {
        val jokeQuantity = 10
        val randomJokeService = mock(RandomJokeService::class.java)
        doReturn(
            JokeRemote(
                id,
                categories,
                createdAt,
                iconUrl,
                updatedAt,
                url,
                value
            )
        ).`when`(randomJokeService).getRandomJoke()

        val jokeService = JokeServiceImpl(
            randomJokeService,
            JokeMapperRemote()
        )

        val jokes = jokeService.getRandomJokes(jokeQuantity)
        Assert.assertEquals(jokeQuantity, jokes.size)
        Assert.assertEquals(id, jokes[0].id)
        Assert.assertEquals(categories, jokes[0].categories)
        Assert.assertEquals(createdAtTimestamp, jokes[0].createdAt.time)
        Assert.assertEquals(iconUrl, jokes[0].iconUrl)
        Assert.assertEquals(updatedAtTimestamp, jokes[0].updatedAt.time)
        Assert.assertEquals(url, jokes[0].url)
        Assert.assertEquals(value, jokes[0].value)
    }
}
