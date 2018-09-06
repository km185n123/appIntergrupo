package com.prueba.julianlopez.appintergrupo.Login.Prospects

import android.content.Context
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.ProspectEntity

interface ProspectsMVP {

    interface View{


        fun loadProspects(prospectsResponse:List<ProspectEntity>)
        fun notificationError(error:String)
    }

    interface Presenter{

        fun notificationError(error:String)
        fun loadProspects(prospectsResponse:List<ProspectEntity>)
        fun Prospects(token:String,context: Context)
    }

    interface Model{

      fun Prospects(token:String,context: Context)
    }
}