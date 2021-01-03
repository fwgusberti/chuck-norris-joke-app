package br.com.nerdrapido.chucknorrisjokeapp.data.repository

import br.com.nerdrapido.chucknorrisjokeapp.data.mapper.JokeMapperData
import br.com.nerdrapido.chucknorrisjokeapp.data.service.JokeService
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import br.com.nerdrapido.chucknorrisjokeapp.domain.repository.JokeRepository

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class JokeRepositoryImpl(
    private val jokeService: JokeService,
    private val jokeMapperData: JokeMapperData
) : JokeRepository {

    override suspend fun getRandomJokes(): List<JokeDomain> {
        return jokeMapperData.mapFromDataToRemoteList(jokeService.getRandomJokes())
    }
}