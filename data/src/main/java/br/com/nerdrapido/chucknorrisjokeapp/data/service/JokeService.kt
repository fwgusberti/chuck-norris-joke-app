package br.com.nerdrapido.chucknorrisjokeapp.data.service

import br.com.nerdrapido.chucknorrisjokeapp.data.model.JokeData

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
interface JokeService {

    suspend fun getRandomJokes(quantity: Int): List<JokeData>
}
