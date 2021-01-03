package br.com.nerdrapido.chucknorrisjokeapp.remote.service

import br.com.nerdrapido.chucknorrisjokeapp.data.model.JokeData
import br.com.nerdrapido.chucknorrisjokeapp.data.service.JokeService
import br.com.nerdrapido.chucknorrisjokeapp.remote.mapper.JokeMapperRemote

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class JokeServiceImpl(
    private val randomJokeService: RandomJokeService,
    private val mapperRemote: JokeMapperRemote
) : JokeService {
    override suspend fun getRandomJokes(quantity: Int): List<JokeData> {
        val remoteJokes = mutableListOf<JokeData>()
        var counter = quantity
        while (counter > 0) {
            remoteJokes.add(mapperRemote.mapFromRemoteToData(randomJokeService.getRandomJoke()))
            counter--
        }

        return remoteJokes
    }
}
