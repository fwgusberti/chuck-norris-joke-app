package br.com.nerdrapido.chucknorrisjokeapp.remote.network

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

/**
 * Created By FELIPE GUSBERTI @ 02/01/2021
 */
class MockServiceInterceptorWithString(private val responseBody: String, private val code: Int) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return Response.Builder()
            .code(code)
            .message(Gson().toJson(responseBody))
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(
                responseBody
                    .toResponseBody("application/json".toMediaTypeOrNull())
            )
            .addHeader("content-type", "application/json")
            .build()
    }
}