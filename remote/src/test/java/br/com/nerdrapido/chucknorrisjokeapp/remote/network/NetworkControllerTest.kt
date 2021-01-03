package br.com.nerdrapido.chucknorrisjokeapp.remote.network

import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations

/**
 * Created By FELIPE GUSBERTI @ 02/01/2021
 */
class NetworkControllerTest {

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `test NetworkController startup`() {
        val networkController = NetworkController()
        val retrofit = networkController.retrofit
    }
}