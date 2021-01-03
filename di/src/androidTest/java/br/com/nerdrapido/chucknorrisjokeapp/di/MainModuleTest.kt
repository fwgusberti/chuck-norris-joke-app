package br.com.nerdrapido.chucknorrisjokeapp.di

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class MainModuleTest : KoinTest {

    private val context = ApplicationProvider.getApplicationContext<Application>()

    @Test
    fun test_EventListActivity_instantiation() {
        startKoin {
            androidContext(context)
            modules(
                MainModule.module
            )
        }
    }
}