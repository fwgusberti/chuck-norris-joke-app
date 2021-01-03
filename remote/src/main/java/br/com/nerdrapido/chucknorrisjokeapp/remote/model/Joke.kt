package br.com.nerdrapido.chucknorrisjokeapp.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Created By FELIPE GUSBERTI @ 02/01/2021
 */
data class Joke(

    /**
     * Id of the joke. Ex.: "HwaAStnqSc6arCrrRoUATw".
     */
    val id: String,

    /**
     * String List containing the categories of the joke.
     * Can be empty.
     */
    val categories: List<String>,

    /**
     * Creation date of the joke in "yyyy-MM-dd HH:mm:ss.S" Format.
     * Ex.: "2020-01-05 13:42:20.841843"
     */
    @SerializedName("created_at")
    val createdAt: String,

    /**
     * Icon URL of the joke.
     * Ex.: https://assets.chucknorris.host/img/avatar/chuck-norris.png
     */
    @SerializedName("icon_url")
    val iconUrl: String,

    /**
     * Last Update date of the Joke in "yyyy-MM-dd HH:mm:ss.S" Format.
     * Ex.: "2020-01-05 13:42:20.841843"
     */
    @SerializedName("updated_at")
    val updatedAt: String,

    /**
     * URL of the joke.
     * Ex.: https://api.chucknorris.io/jokes/HwaAStnqSc6arCrrRoUATw
     */
    val url: String,

    /**
     * The actual Joke itself.
     */
    val value: String
)
