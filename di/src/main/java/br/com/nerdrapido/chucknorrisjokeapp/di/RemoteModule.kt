package br.com.nerdrapido.chucknorrisjokeapp.di

import br.com.nerdrapido.chucknorrisjokeapp.data.service.JokeService
import br.com.nerdrapido.chucknorrisjokeapp.remote.mapper.JokeMapperRemote
import br.com.nerdrapido.chucknorrisjokeapp.remote.network.NetworkController
import br.com.nerdrapido.chucknorrisjokeapp.remote.service.JokeServiceImpl
import br.com.nerdrapido.chucknorrisjokeapp.remote.service.RandomJokeService
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
object RemoteModule {
    private val networkController = NetworkController()

    val module = module {
        single { JokeMapperRemote() }
        single<RandomJokeService> { networkController.retrofit.create(RandomJokeService::class.java) }
        single<JokeService> { JokeServiceImpl(get(), get()) }
    }
}
