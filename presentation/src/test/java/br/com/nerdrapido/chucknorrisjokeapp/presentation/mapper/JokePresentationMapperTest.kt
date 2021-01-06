package br.com.nerdrapido.chucknorrisjokeapp.presentation.mapper

import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.mapper.JokePresentationMapper
import org.junit.Assert
import org.junit.Test
import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 05/01/2021
 */
class JokePresentationMapperTest {

    private val id: String = "12345asdasd"
    private val categories: List<String> = emptyList()
    private val createdAtTimestamp = 1578232581843L
    private val iconUrl: String = "oidhgfoirdsg"
    private val updatedAtTimestamp = 1578318981843L
    private val url: String = "lzsidjflçdjf"
    private val value: String = "lzsidjflçdjf asdasd asdasd"

    private val jokeMapper = JokePresentationMapper()

    @Test
    fun `test mapFromDomainToPresentation on JokeDomainMapper`() {
        val jokeDomain = JokeDomain(
            id,
            categories,
            Date(createdAtTimestamp),
            iconUrl,
            Date(updatedAtTimestamp),
            url,
            value
        )
        val joke = jokeMapper.mapFromDomainToPresentation(jokeDomain)

        Assert.assertEquals(id, joke.id)
        Assert.assertEquals(categories, joke.categories)
        Assert.assertEquals(createdAtTimestamp, joke.createdAt.time)
        Assert.assertEquals(iconUrl, joke.iconUrl)
        Assert.assertEquals(updatedAtTimestamp, joke.updatedAt.time)
        Assert.assertEquals(url, joke.url)
        Assert.assertEquals(value, joke.value)
    }

    @Test
    fun `test mapFromDomainToPresentationList on JokeDomainMapper`() {
        val jokeDomain = listOf(
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
        val joke = jokeMapper.mapFromDomainToPresentationList(jokeDomain)
        Assert.assertTrue(joke.size == 1)
        Assert.assertEquals(id, joke[0].id)
        Assert.assertEquals(categories, joke[0].categories)
        Assert.assertEquals(createdAtTimestamp, joke[0].createdAt.time)
        Assert.assertEquals(iconUrl, joke[0].iconUrl)
        Assert.assertEquals(updatedAtTimestamp, joke[0].updatedAt.time)
        Assert.assertEquals(url, joke[0].url)
        Assert.assertEquals(value, joke[0].value)
    }

}