package br.com.nerdrapido.chucknorrisjokeapp.remote.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created By FELIPE GUSBERTI @ 02/01/2021
 */

private const val SERVER_URL = "https://api.chucknorris.io/jokes/"

class NetworkController(
    private val serviceInterceptor: Interceptor? = null
) {

    val retrofit: Retrofit = Retrofit.Builder()
        .client(getInterceptor())
        .baseUrl(SERVER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Returns an OkHttpClient.
     */
    private fun getInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            serviceInterceptor?.let {
                builder.addInterceptor(serviceInterceptor)
            }
        return builder.build()
    }
}