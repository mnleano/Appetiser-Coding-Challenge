package com.neds.appetisercodingchallenge.dataLayer

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
class Recent(
    @Id
    var id: Long = 0,
    val trackId: Int,
    val icon: String?,
    val kind: String?,
    val title: String?,
    val artist: String?,
    val currency: String?,
    val price: Double?,
    val genre: String?,
    val releaseDate: String?,
    val description: String?
)