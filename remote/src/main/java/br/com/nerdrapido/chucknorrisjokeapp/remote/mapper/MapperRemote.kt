package br.com.nerdrapido.chucknorrisjokeapp.remote.mapper

import br.com.nerdrapido.chucknorrisjokeapp.data.model.DataEntity
import br.com.nerdrapido.chucknorrisjokeapp.remote.model.RemoteEntity

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
interface MapperRemote<R : RemoteEntity, D : DataEntity> {

    /**
     * Maps an Remote object entity to Data object.
     */
    fun mapFromRemoteToData(type: R): D

}
