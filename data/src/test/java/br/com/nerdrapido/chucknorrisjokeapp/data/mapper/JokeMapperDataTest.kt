package br.com.nerdrapido.chucknorrisjokeapp.data.mapper

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

    @Test
    fun `test mapFromDataToDomainList on JokeMapperData`() {
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
        val jokeDomain = jokeMapper.mapFromDataToRemoteList(jokeData)
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