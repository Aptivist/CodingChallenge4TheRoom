package com.aptivist.roomchallengeone.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MemeResponseDTO(
    @SerializedName("data")
    val `data`: DataDTO,
    @SerializedName("success")
    val success: Boolean
)