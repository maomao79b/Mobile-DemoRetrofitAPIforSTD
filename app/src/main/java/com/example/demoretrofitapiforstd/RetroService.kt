package com.example.demoretrofitapiforstd

import retrofit2.Call
import retrofit2.http.*

interface RetroService {
    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 9b728ec1af635452f7ed7493588c36fceba52dbbcf1a2ecb08c6cf20747b666e")
    fun getUserList(): Call<ArrayList<User>>

    @GET("users")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 9b728ec1af635452f7ed7493588c36fceba52dbbcf1a2ecb08c6cf20747b666e")
    fun searchUsers(@Query("name") searchText:String):Call<ArrayList<User>>

    @GET("users{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUser(@Path("user_id")user_id:String):Call<ArrayList<User>>

    @POST("users")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 9b728ec1af635452f7ed7493588c36fceba52dbbcf1a2ecb08c6cf20747b666e")
    fun createUser(@Body params: User): Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 9b728ec1af635452f7ed7493588c36fceba52dbbcf1a2ecb08c6cf20747b666e")
    fun updateUser(@Path("user_id")user_id: String, @Body params: User): Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers("Accept:application/json", "Content-Type:application/json",
        "Authorization: Bearer 9b728ec1af635452f7ed7493588c36fceba52dbbcf1a2ecb08c6cf20747b666e")
    fun deleteUser(@Path("user_id") user_id: String): Call<UserResponse>
}