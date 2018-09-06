package com.prueba.julianlopez.appintergrupo.Login.Login.Presenter

import android.content.Context
import com.prueba.julianlopez.appintergrupo.Login.Login.LoginMVP
import com.prueba.julianlopez.appintergrupo.Login.Login.Model.Login

class Login(var view: LoginMVP.View) : LoginMVP.Presenter {
    var model: LoginMVP.Model


    init {
        model = Login(this)
    }

    override fun notificationSuccess(token: String, network: Boolean) {

        if (view != null)
            view.notificationSuccess(token,network)

    }


    override fun notificationError(error: String) {

        if (view != null)
            view.notificationError(error)

    }



    override fun login(email: String, password: String, context: Context) {

        model.login(email,password,context)
    }
}