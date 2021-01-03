package br.com.nerdrapido.chucknorrisjokeapp.remote.mapper

import br.com.nerdrapido.chucknorrisjokeapp.remote.model.JokeRemote
import org.junit.Assert
import org.junit.Test

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class JokeMapperRemoteTest {

    private val id: String = "12345asdasd"
    private val categories: List<String> = emptyList()
    private val createdAt: String = "2020-01-05 13:42:20.841843"
    private val createdAtTimestamp = 1578232581843L
    private val iconUrl: String = "oidhgfoirdsg"
    private val updatedAt: String = "2020-01-06 13:42:20.841843"
    private val updatedAtTimestamp = 1578318981843L
    private val url: String = "lzsidjflçdjf"
    private val value: String = "lzsidjflçdjf asdasd asdasd"

    private val jokeMapper = JokeMapperRemote()

    @Test
    fun `test mapFromRemoteToData on JokeMapper`() {
        val jokeRemote = JokeRemote(
            id,
            categories,
            createdAt,
            iconUrl,
            updatedAt,
            url,
            value
        )
        val jokeData = jokeMapper.mapFromRemoteToData(jokeRemote)

        Assert.assertEquals(id, jokeData.id)
        Assert.assertEquals(categories, jokeData.categories)
        Assert.assertEquals(createdAtTimestamp, jokeData.createdAt.time)
        Assert.assertEquals(iconUrl, jokeData.iconUrl)
        Assert.assertEquals(updatedAtTimestamp, jokeData.updatedAt.time)
        Assert.assertEquals(url, jokeData.url)
        Assert.assertEquals(value, jokeData.value)
    }
}