package br.com.nerdrapido.chucknorrisjokeapp.domain.usecase

import br.com.nerdrapido.chucknorrisjokeapp.domain.model.EntityWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
interface UseCase<I : UseCaseInput?, O : Any> {

    /**
     * Executes the use case.
     */
    suspend fun execute(input: I): O

    suspend fun <T : Any> safeRepositoryCall(
        call: suspend () -> T
    ): EntityWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                EntityWrapper.Success(call.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> EntityWrapper.NetworkError(throwable)
                    else -> EntityWrapper.GenericError(throwable)
                }
            }
        }
    }
}