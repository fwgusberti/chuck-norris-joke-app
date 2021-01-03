package br.com.nerdrapido.chucknorrisjokeapp.domain.repository

import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
interface JokeRepository {

    /**
     * @return a random list of jokes.
     */
    suspend fun getRandomJokes(quantity : Int) : List<JokeDomain>
}