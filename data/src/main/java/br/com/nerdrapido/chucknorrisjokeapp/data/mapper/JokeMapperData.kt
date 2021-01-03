package br.com.nerdrapido.chucknorrisjokeapp.data.mapper

import br.com.nerdrapido.chucknorrisjokeapp.data.model.JokeData
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class JokeMapperData : MapperData<JokeData, JokeDomain> {

    override fun mapFromDataToRemote(type: JokeData): JokeDomain {
        return JokeDomain(
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
