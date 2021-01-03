package br.com.nerdrapido.chucknorrisjokeapp.data.model

import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
data class JokeData(
    val id: String,
    val categories: List<String>,
    val createdAt: Date,
    val iconUrl: String,
    val updatedAt: Date,
    val url: String,
    val value: String
) : DataEntity