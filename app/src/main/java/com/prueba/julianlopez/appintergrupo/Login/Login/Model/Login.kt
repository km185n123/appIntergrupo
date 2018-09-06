package com.prueba.julianlopez.appintergrupo.Login.Login.Model

import android.content.Context
import com.google.gson.Gson
import com.prueba.julianlopez.appintergrupo.Login.Login.LoginMVP
import com.prueba.julianlopez.appintergrupo.Login.Rest.*
import com.prueba.julianlopez.appintergrupo.Login.Util.ValidateConexion
import com.prueba.julianlopez.appintergrupo.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class Login: LoginMVP.Model{


    private var presenter: LoginMVP.Presenter
    private val restApiAdapter = RestApiAdapter()
    private val retrofit = restApiAdapter.conectionApiRest()

    constructor(presenter: LoginMVP.Presenter) {
        this.presenter = presenter
    }


    override fun login(email: String, password: String, context: Context) {


            val response : Call<LoginResponse> = retrofit
                    .create(EndPointLogin::class.java)
                    .updateDataProfile(email,password)

            response.enqueue(object : Callback<LoginResponse> {

                override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {

                    if (response!!.code() == 200 && response.body() != null){

                        presenter.notificationSuccess(response.body()!!.authToken.toString(),true)


                    }else{
                        try {
                            val loginErrorResponse: LoginErrorResponse = Gson().fromJson(response.errorBody()!!.string(), LoginErrorResponse::class.java)
                            presenter.notificationError(loginErrorResponse.error.toString())
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }

                    }
                }

                override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {

                    if (ValidateConexion.isOnline(context)){
                        presenter.notificationError(context.resources.getString(R.string.mesage_without_internet))
                        presenter.notificationSuccess("",false)
                        return
                    }
                    presenter.notificationError(t!!.message.toString())

                }



            })



    }


}