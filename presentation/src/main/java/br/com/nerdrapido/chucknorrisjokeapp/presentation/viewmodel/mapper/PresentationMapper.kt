package br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.mapper

import br.com.nerdrapido.chucknorrisjokeapp.domain.model.DomainEntity
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.model.Entity

/**
 * Created By FELIPE GUSBERTI @ 05/01/2021
 */
interface PresentationMapper<D : DomainEntity, P : Entity> {

    /**
     * Maps an Remote object entity to Data object.
     */
    fun mapFromDomainToPresentation(type: D): P

    /**
     * Maps an Remote objects entity to Data objects List.
     */
    fun mapFromDomainToPresentationList(types: List<D>): List<P> {
        val output = mutableListOf<P>()
        types.forEach {
            output.add(mapFromDomainToPresentation(it))
        }
        return output
    }
}
