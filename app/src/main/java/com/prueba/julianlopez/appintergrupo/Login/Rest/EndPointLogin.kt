package com.prueba.julianlopez.appintergrupo.Login.Rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface EndPointLogin {


    @GET(ConstantsRestApi.END_LOGIN)
    fun updateDataProfile(
            @Query("email") email:String,
            @Query("password") password:String
    ): Call<LoginResponse>

}