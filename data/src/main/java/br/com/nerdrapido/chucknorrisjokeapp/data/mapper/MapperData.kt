package br.com.nerdrapido.chucknorrisjokeapp.data.mapper

import br.com.nerdrapido.chucknorrisjokeapp.data.model.DataEntity
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.DomainEntity

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
interface MapperData<D : DataEntity, O : DomainEntity> {

    /**
     * Maps an Remote object entity to Data object.
     */
    fun mapFromDataToRemote(type: D): O

    /**
     * Maps an Remote objects entity to Data objects List.
     */
    fun mapFromDataToRemoteList(types: List<D>): List<O> {
        val output = mutableListOf<O>()
        types.forEach {
            output.add(mapFromDataToRemote(it))
        }
        return output
    }

}
