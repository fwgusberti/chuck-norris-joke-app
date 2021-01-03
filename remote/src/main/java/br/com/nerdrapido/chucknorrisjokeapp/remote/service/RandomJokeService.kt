package br.com.nerdrapido.chucknorrisjokeapp.remote.service

import br.com.nerdrapido.chucknorrisjokeapp.remote.model.JokeRemote
import retrofit2.http.GET

/**
 * Created By FELIPE GUSBERTI @ 02/01/2021
 */
interface RandomJokeService {

    /**
     * Returns a ramdom Joke.
     */
    @GET("random")
    suspend fun getRandomJoke(): JokeRemote

}
