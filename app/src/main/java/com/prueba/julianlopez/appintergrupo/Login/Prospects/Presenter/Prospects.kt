package com.prueba.julianlopez.appintergrupo.Login.Prospects.Presenter

import android.content.Context
import com.prueba.julianlopez.appintergrupo.Login.Prospects.Model.Prospects
import com.prueba.julianlopez.appintergrupo.Login.Prospects.ProspectsMVP
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.ProspectEntity

class Prospects( var view: ProspectsMVP.View) :ProspectsMVP.Presenter {



    var model:ProspectsMVP.Model

    init {
        model = Prospects(this)
    }


    override fun loadProspects(prospectsResponse: List<ProspectEntity>) {

        if (view != null)
             view.loadProspects(prospectsResponse)
    }

    override fun notificationError(error: String) {

        if (view != null)
            view.notificationError(error)
    }

    override fun Prospects(token: String, context: Context) {

        model.Prospects(token,context)
    }


}