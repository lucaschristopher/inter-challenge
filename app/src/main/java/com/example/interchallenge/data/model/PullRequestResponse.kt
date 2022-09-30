package com.example.interchallenge.data.model

import com.google.gson.annotations.SerializedName

class PullRequestResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("html_url") val url: String,
    @SerializedName("state") val state: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String,
    @SerializedName("updated_at") val updatedAt: String
)