@file:Suppress("unused")

package br.com.nerdrapido.chucknorrisjokeapp.application

import android.app.Application
import br.com.nerdrapido.chucknorrisjokeapp.di.MainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class StartApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Inicialização do Timber para árvore de logs
        Timber.plant(Timber.DebugTree())

        // Inicialização do koin. Deve ser inicializado aqui.
        startKoin {
            // declara o contexto do Android.
            androidContext(this@StartApplication)
            // declara o(s) módulo(s).
            modules(MainModule.module)
        }
    }
}