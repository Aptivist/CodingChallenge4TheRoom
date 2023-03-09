package com.aptivist.roomchallengeone.data.remote.mappers

import com.aptivist.roomchallengeone.data.remote.dto.MemeDTO
import com.aptivist.roomchallengeone.domain.models.MemeItem

fun MemeDTO.toMemeItem() : MemeItem {
    return MemeItem(this.name, this.url)
}