package br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.mapper

import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.model.Joke

/**
 * Created By FELIPE GUSBERTI @ 05/01/2021
 */
class JokePresentationMapper : PresentationMapper<JokeDomain, Joke> {

    override fun mapFromDomainToPresentation(type: JokeDomain): Joke {
        return Joke(
            type.id,
            type.categories,
            type.createdAt,
            type.iconUrl,
            type.updatedAt,
            type.url,
            type.value
        )
    }
}