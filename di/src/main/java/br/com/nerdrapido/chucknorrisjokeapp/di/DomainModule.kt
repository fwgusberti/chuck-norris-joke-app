package br.com.nerdrapido.chucknorrisjokeapp.di

import br.com.nerdrapido.chucknorrisjokeapp.domain.usecase.RandomJokesUseCase
import br.com.nerdrapido.chucknorrisjokeapp.domain.usecase.RandomJokesUseCaseImpl
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
object DomainModule {
    val module = module {
        single<RandomJokesUseCase> { RandomJokesUseCaseImpl(get()) }
    }
}
