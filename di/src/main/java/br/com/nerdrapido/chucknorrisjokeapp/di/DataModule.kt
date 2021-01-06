package br.com.nerdrapido.chucknorrisjokeapp.di

import br.com.nerdrapido.chucknorrisjokeapp.data.mapper.JokeMapperData
import br.com.nerdrapido.chucknorrisjokeapp.data.repository.JokeRepositoryImpl
import br.com.nerdrapido.chucknorrisjokeapp.domain.repository.JokeRepository
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
object DataModule {
    val module = module {
        // Mapper
        single { JokeMapperData() }
        // Repos
        single<JokeRepository> { JokeRepositoryImpl(get(), get()) }
    }
}
