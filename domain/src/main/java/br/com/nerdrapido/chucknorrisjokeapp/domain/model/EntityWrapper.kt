package br.com.nerdrapido.chucknorrisjokeapp.domain.model

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
sealed class EntityWrapper<out T : Any> {

    /**
     * Success on data Acquisition.
     */
    data class Success<out T : Any>(val value: T) : EntityWrapper<T>()

    /**
     * Network error on data acquisition.
     */
    data class NetworkError(val error: Throwable) : EntityWrapper<Nothing>()

    /**
     * Generic error.
     */
    data class GenericError(val error: Throwable) : EntityWrapper<Nothing>()
}