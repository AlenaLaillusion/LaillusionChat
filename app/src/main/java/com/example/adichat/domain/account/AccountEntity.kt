package com.example.adichat.domain.account

import com.google.gson.annotations.SerializedName

data class AccountEntity(
    @SerializedName("user_id")
    val id: Long,
    var name: String,
    var email: String,
    @SerializedName("token")
    val token: String,
    var status: String,
    @SerializedName("user_date")
    val userDate: Long,
    var image: String,
    var password: String

)