package br.com.nerdrapido.chucknorrisjokeapp.domain.usecase

import br.com.nerdrapido.chucknorrisjokeapp.domain.model.EntityWrapper
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain

/**
 * Created By FELIPE GUSBERTI @ 04/01/2021
 */
interface RandomJokesUseCase :
    UseCase<RandomJokesUseCase.Input?, EntityWrapper<List<JokeDomain>>> {

    data class Input(
        val quantity: Int = 1
    ) : UseCaseInput
}