package com.prueba.julianlopez.appintergrupo.Login.Rest

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RestApiAdapter {

    fun conectionApiRest(): Retrofit {




        val gson = GsonBuilder()
                .setDateFormat("dd-MM-yyyy")
                .setLenient()
                .create()


        val retrofit:Retrofit = Retrofit.Builder()
                .baseUrl(ConstantsRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

        return retrofit

    }
}