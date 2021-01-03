package br.com.nerdrapido.chucknorrisjokeapp.remote.mapper

import br.com.nerdrapido.chucknorrisjokeapp.data.model.JokeData
import br.com.nerdrapido.chucknorrisjokeapp.remote.model.JokeRemote
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class JokeMapperRemote : MapperRemote<JokeRemote, JokeData> {

    override fun mapFromRemoteToData(type: JokeRemote): JokeData {
        return JokeData(
            type.id,
            type.categories,
            mapRemoteDateToDataDate(type.createdAt),
            type.iconUrl,
            mapRemoteDateToDataDate(type.updatedAt),
            type.url,
            type.value
        )
    }

    /**
     * Convert date string in "yyyy-MM-dd HH:mm:ss.S" Format to [Date] object.
     * @param remoteDate is an String formatted as "yyyy-MM-dd HH:mm:ss.S"
     * ex.: "2020-01-05 13:42:20.841843".
     * @return Date Object
     */
    private fun mapRemoteDateToDataDate(remoteDate: String) : Date {
        val format = "yyyy-MM-dd HH:mm:ss.S"
        val dateFormat = SimpleDateFormat(format, Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        return dateFormat.parse(remoteDate)
    }
}