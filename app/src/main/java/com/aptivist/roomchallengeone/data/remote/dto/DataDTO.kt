package com.aptivist.roomchallengeone.data.remote.dto


import com.google.gson.annotations.SerializedName

data class DataDTO(
    @SerializedName("memes")
    val memes: List<MemeDTO>
)