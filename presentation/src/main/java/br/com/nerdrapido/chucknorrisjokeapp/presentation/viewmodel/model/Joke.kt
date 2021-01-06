package br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.model

import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 05/01/2021
 */
data class Joke(
    val id: String,
    val categories: List<String>,
    val createdAt: Date,
    val iconUrl: String,
    val updatedAt: Date,
    val url: String,
    val value: String
) : Entity