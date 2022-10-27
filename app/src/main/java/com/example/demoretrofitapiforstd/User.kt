package com.example.demoretrofitapiforstd

data class User (val id: String?, val name: String?, val email: String?, val status: String?, val gender: String?)
data class UserResponse(val cod: Int?, val meta: String?, val data: User?)