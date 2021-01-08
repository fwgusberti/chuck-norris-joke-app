package br.com.nerdrapido.chucknorrisjokeapp.di

import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist.JokeViewModel
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.mapper.JokePresentationMapper
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.splash.SplashScreenViewModel
import org.koin.dsl.module

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
object PresentationModule {
    val module = module {
        single { JokePresentationMapper() }
        factory { SplashScreenViewModel() }
        factory(override = true) { JokeViewModel(get(), get()) }
    }
}
