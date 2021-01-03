package br.com.nerdrapido.chucknorrisjokeapp.domain.model

import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class JokeDomain(
    val id: String,
    val categories: List<String>,
    val createdAt: Date,
    val iconUrl: String,
    val updatedAt: Date,
    val url: String,
    val value: String
) : DomainEntity