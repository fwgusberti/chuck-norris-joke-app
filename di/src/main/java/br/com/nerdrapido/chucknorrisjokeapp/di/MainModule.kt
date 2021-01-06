package br.com.nerdrapido.chucknorrisjokeapp.di

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
object MainModule {
    val module = listOf(
        RemoteModule.module,
        DataModule.module,
        DomainModule.module,
        PresentationModule.module
    )
}