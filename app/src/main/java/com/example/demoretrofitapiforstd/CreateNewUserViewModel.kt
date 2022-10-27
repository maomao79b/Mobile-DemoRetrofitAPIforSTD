package com.example.demoretrofitapiforstd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class CreateNewUserViewModel:ViewModel() {
    lateinit var createNewUserLiveData:MutableLiveData<UserResponse?>
    lateinit var updateUserLiveData:MutableLiveData<UserResponse?>
    lateinit var deleteUserLiveData:MutableLiveData<UserResponse?>

    init{
        createNewUserLiveData = MutableLiveData()
        updateUserLiveData = MutableLiveData()
        deleteUserLiveData = MutableLiveData()
    }
    fun getCreateNewUserObserver():MutableLiveData<UserResponse?>{
        return createNewUserLiveData
    }
    fun getUpdateUserObserver():MutableLiveData<UserResponse?>{
        return updateUserLiveData
    }
    fun getDeleteUserObserver():MutableLiveData<UserResponse?>{
        return deleteUserLiveData
    }
    fun createNewUser(user:User){
        val retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroService.createUser(user)
        call.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                createNewUserLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                createNewUserLiveData.postValue(null)
            }
        })
    }
    fun updateUser(user_id:String, user:User){
        val retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroService.updateUser(user_id, user)
        call.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                updateUserLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                updateUserLiveData.postValue(null)
            }
        })
    }
    fun deleteUser(user_id:String?){
        val retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroService.deleteUser(user_id!!)
        call.enqueue(object : Callback<UserResponse>{
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                deleteUserLiveData.postValue(response.body())
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                deleteUserLiveData.postValue(null)
            }
        })
    }
}