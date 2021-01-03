package br.com.nerdrapido.chucknorrisjokeapp.remote.service

import br.com.nerdrapido.chucknorrisjokeapp.data.model.JokeData
import br.com.nerdrapido.chucknorrisjokeapp.data.service.JokeService

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class JokeServiceImpl(
    private val randomJokeService: RandomJokeService
) : JokeService {
    override suspend fun getRandomJokes(): List<JokeData> {
        TODO("Not yet implemented")
    }
}
