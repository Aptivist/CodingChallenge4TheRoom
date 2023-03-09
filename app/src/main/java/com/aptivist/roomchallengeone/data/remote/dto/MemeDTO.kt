package com.aptivist.roomchallengeone.data.remote.dto


import com.google.gson.annotations.SerializedName

data class MemeDTO(
    @SerializedName("box_count")
    val boxCount: Int,
    @SerializedName("captions")
    val captions: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("uri")
    val url: String,
    @SerializedName("width")
    val width: Int
)