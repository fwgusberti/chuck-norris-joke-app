package br.com.nerdrapido.chucknorrisjokeapp.data

import br.com.nerdrapido.chucknorrisjokeapp.data.mapper.JokeMapperData
import br.com.nerdrapido.chucknorrisjokeapp.data.model.JokeData
import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class JokeMapperDataTest {

    private val id: String = "12345asdasd"
    private val categories: List<String> = emptyList()
    private val createdAtTimestamp = 1578232581843L
    private val iconUrl: String = "oidhgfoirdsg"
    private val updatedAtTimestamp = 1578318981843L
    private val url: String = "lzsidjflçdjf"
    private val value: String = "lzsidjflçdjf asdasd asdasd"

    private val jokeMapper = JokeMapperData()

    @Test
    fun `test mapFromDataToDomain on JokeMapperData`() {
        val jokeData = JokeData(
            id,
            categories,
            Date(createdAtTimestamp),
            iconUrl,
            Date(updatedAtTimestamp),
            url,
            value
        )
        val jokeDomain = jokeMapper.mapFromDataToRemote(jokeData)

        Assert.assertEquals(id, jokeDomain.id)
        Assert.assertEquals(categories, jokeDomain.categories)
        Assert.assertEquals(createdAtTimestamp, jokeDomain.createdAt.time)
        Assert.assertEquals(iconUrl, jokeDomain.iconUrl)
        Assert.assertEquals(updatedAtTimestamp, jokeDomain.updatedAt.time)
        Assert.assertEquals(url, jokeDomain.url)
        Assert.assertEquals(value, jokeDomain.value)
    }
}