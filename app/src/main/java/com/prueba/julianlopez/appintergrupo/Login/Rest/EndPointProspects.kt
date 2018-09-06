package com.prueba.julianlopez.appintergrupo.Login.Rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface EndPointProspects {


    @GET(ConstantsRestApi.END_PROSPECTS)
    fun pronspects(
            @Header("token") token:String

    ): Call<List<Prospect>>

}