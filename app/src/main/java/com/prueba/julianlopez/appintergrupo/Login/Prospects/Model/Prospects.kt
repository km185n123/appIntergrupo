package com.prueba.julianlopez.appintergrupo.Login.Prospects.Model

import android.content.Context
import com.google.gson.Gson
import com.prueba.julianlopez.appintergrupo.Login.Prospects.ProspectsMVP
import com.prueba.julianlopez.appintergrupo.Login.Rest.*
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.AppDatabase
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.ProspectEntity
import com.prueba.julianlopez.appintergrupo.Login.Util.ValidateConexion
import com.prueba.julianlopez.appintergrupo.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class Prospects( var presenter: ProspectsMVP.Presenter) :ProspectsMVP.Model {


    private val restApiAdapter = RestApiAdapter()
    private val retrofit = restApiAdapter.conectionApiRest()




    override fun Prospects(token: String, context: Context) {


        val db:AppDatabase = AppDatabase.getAppDatabase(context)
        val response : Call<List<Prospect>> = retrofit
                .create(EndPointProspects::class.java)
                .pronspects(token)

        response.enqueue(object : Callback<List<Prospect>> {

            override fun onResponse(call: Call<List<Prospect>>?, response: Response<List<Prospect>>?) {

                if (response!!.code() == 200 && response.body() != null) {



                    db.ProspectDao().nukeTable()
                    response.body()!!.forEachIndexed { index, prospect ->

                        val prospectEntity: ProspectEntity = ProspectEntity()
                        syncUpTables(prospectEntity,prospect)

                        db.ProspectDao().insert(prospectEntity)
                    }


                    var  listOrospect = db.ProspectDao().getAll()
                    presenter.loadProspects(listOrospect)



                } else {
                    try {
                        val loginErrorResponse: LoginErrorResponse = Gson().fromJson(response.errorBody()!!.string(), LoginErrorResponse::class.java)
                        presenter.notificationError(loginErrorResponse.error.toString())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }

            }



            override fun onFailure(call: Call<List<Prospect>>, t: Throwable) {

                if (ValidateConexion.isOnline(context)){
                    presenter.notificationError(context.resources.getString(R.string.mesage_without_internet))
                    var  listOrospect = db.ProspectDao().getAll()
                    presenter.loadProspects(listOrospect)
                    return
                }
                presenter.notificationError(t!!.message.toString())

            }
        })


    }

    private fun syncUpTables(prospectEntity: ProspectEntity, prospect: Prospect) {



        prospectEntity. name= prospect.name
        prospectEntity. surname= prospect.surname
        prospectEntity. telephone= prospect.telephone
        prospectEntity. schProspectIdentification= prospect.schProspectIdentification
        prospectEntity. address= prospect.address
        prospectEntity. createdAt= prospect.createdAt
        prospectEntity. updatedAt= prospect.updatedAt
        prospectEntity. statusCd= prospect.statusCd
        prospectEntity. zoneCode= prospect.zoneCode
        prospectEntity. neighborhoodCode= prospect.neighborhoodCode
        prospectEntity. cityCode= prospect.cityCode
        prospectEntity. sectionCode= prospect.sectionCode
        prospectEntity. roleId= prospect.roleId
        prospectEntity. appointableId= prospect.appointableId
        prospectEntity. rejectedObservation= prospect.rejectedObservation
        prospectEntity. observation= prospect.observation
        prospectEntity. isDisable= prospect.isDisable
        prospectEntity. isVisited= prospect.isVisited
        prospectEntity. isCallcenter= prospect.isCallcenter
        prospectEntity. isAcceptSearch= prospect.isAcceptSearch
        prospectEntity. campaignCode= prospect.campaignCode
        prospectEntity. userId= prospect.userId
        prospectEntity. updated= prospect.updated

    }
}