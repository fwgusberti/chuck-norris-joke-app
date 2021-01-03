package br.com.nerdrapido.chucknorrisjokeapp.remote.service

import br.com.nerdrapido.chucknorrisjokeapp.remote.network.MockServiceInterceptorWithString
import br.com.nerdrapido.chucknorrisjokeapp.remote.network.NetworkController
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.HttpURLConnection.HTTP_OK

/**
 * Created By FELIPE GUSBERTI @ 02/01/2021
 */
class RandomJokeServiceTest {

    private val id: String = "12345asdasd"
    private val categories: List<String> = emptyList()
    private val createdAt: String = "2020-01-05 13:42:20.841843"
    private val iconUrl: String = "oidhgfoirdsg"
    private val updatedAt: String = "2020-01-05 13:42:20.841843"
    private val url: String = "lzsidjflçdjf"
    private val value: String = "lzsidjflçdjf asdasd asdasd"

    @Test
    fun `test getRandomJokeService`() {

        val responseBody =
            "{\"categories\":[],\"created_at\":\"$createdAt\",\"icon_url\":\"$iconUrl\",\"id\":\"$id\",\"updated_at\":\"$updatedAt\",\"url\":\"$url\",\"value\":\"$value\"}"
        val interceptor = MockServiceInterceptorWithString(
            responseBody,
            HTTP_OK
        )
        val networkController = NetworkController(interceptor)
        val service = networkController.retrofit.create(RandomJokeService::class.java)
        val joke = runBlocking {
            service.getRandomJoke()
        }
        Assert.assertEquals(id, joke.id)
        Assert.assertEquals(categories, joke.categories)
        Assert.assertEquals(createdAt, joke.createdAt)
        Assert.assertEquals(iconUrl, joke.iconUrl)
        Assert.assertEquals(updatedAt, joke.updatedAt)
        Assert.assertEquals(url, joke.url)
        Assert.assertEquals(value, joke.value)
    }
}