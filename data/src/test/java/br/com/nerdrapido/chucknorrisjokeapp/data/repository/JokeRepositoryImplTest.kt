package br.com.nerdrapido.chucknorrisjokeapp.data.repository

import br.com.nerdrapido.chucknorrisjokeapp.data.mapper.JokeMapperData
import br.com.nerdrapido.chucknorrisjokeapp.data.model.JokeData
import br.com.nerdrapido.chucknorrisjokeapp.data.service.JokeService
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class JokeRepositoryImplTest {

    private val id: String = "12345asdasd"
    private val categories: List<String> = emptyList()
    private val createdAtTimestamp = 1578232581843L
    private val iconUrl: String = "oidhgfoirdsg"
    private val updatedAtTimestamp = 1578318981843L
    private val url: String = "lzsidjflçdjf"
    private val value: String = "lzsidjflçdjf asdasd asdasd"

    @Test
    fun `test JokeRepositoryImpl getRandomJokes`() {

        val jokeData = listOf(
            JokeData(
                id,
                categories,
                Date(createdAtTimestamp),
                iconUrl,
                Date(updatedAtTimestamp),
                url,
                value
            )
        )

        runBlocking {
            val service = mock(JokeService::class.java)
            val repository = JokeRepositoryImpl(
                service,
                JokeMapperData()
            )

            doReturn(
                jokeData
            ).`when`(service).getRandomJokes()
            val jokeDomain = repository.getRandomJokes()
            Assert.assertTrue(jokeDomain.size == 1)
            Assert.assertEquals(id, jokeDomain[0].id)
            Assert.assertEquals(categories, jokeDomain[0].categories)
            Assert.assertEquals(createdAtTimestamp, jokeDomain[0].createdAt.time)
            Assert.assertEquals(iconUrl, jokeDomain[0].iconUrl)
            Assert.assertEquals(updatedAtTimestamp, jokeDomain[0].updatedAt.time)
            Assert.assertEquals(url, jokeDomain[0].url)
            Assert.assertEquals(value, jokeDomain[0].value)
        }
    }
}
