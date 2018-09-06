package com.prueba.julianlopez.appintergrupo.Login.Login

import android.content.Context

interface LoginMVP {

    interface View{

        fun notificationError(error:String)
        fun notificationSuccess(token:String,network:Boolean)

    }

    interface Presenter{

        fun notificationSuccess(token:String,network:Boolean)
        fun notificationError(error:String)
        fun login(email:String , password:String, context: Context)
    }

    interface Model{

        fun login(email:String , password:String, context: Context)
    }
}