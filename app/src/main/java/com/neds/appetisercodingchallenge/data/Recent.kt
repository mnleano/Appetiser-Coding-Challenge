package com.neds.appetisercodingchallenge.data

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Uid
import io.objectbox.relation.ToMany

@Entity
class Recent(
    @Id
    var id: Long = 0,
    @Uid(8145372662748735354L)
    val trackId: Long,
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