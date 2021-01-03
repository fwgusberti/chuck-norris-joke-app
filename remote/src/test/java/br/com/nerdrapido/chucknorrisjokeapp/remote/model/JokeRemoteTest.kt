package br.com.nerdrapido.chucknorrisjokeapp.remote.model

import org.junit.Assert
import org.junit.Test

/**
 * Created By FELIPE GUSBERTI @ 02/01/2021
 */
class JokeRemoteTest {

    private val id: String = "12345asdasd"
    private val categories: List<String> = emptyList()
    private val createdAt: String = "2020-01-05 13:42:20.841843"
    private val iconUrl: String = "oidhgfoirdsg"
    private val updatedAt: String = "2020-01-05 13:42:20.841843"
    private val url: String = "lzsidjflçdjf"
    private val value: String = "lzsidjflçdjf asdasd asdasd"

    @Test
    fun `Joke hydrate test`() {
        val joke = JokeRemote(
            id,
            categories,
            createdAt,
            iconUrl,
            updatedAt,
            url,
            value
        )
        Assert.assertEquals(id, joke.id)
        Assert.assertEquals(categories, joke.categories)
        Assert.assertEquals(createdAt, joke.createdAt)
        Assert.assertEquals(iconUrl, joke.iconUrl)
        Assert.assertEquals(updatedAt, joke.updatedAt)
        Assert.assertEquals(url, joke.url)
        Assert.assertEquals(value, joke.value)
    }
}
