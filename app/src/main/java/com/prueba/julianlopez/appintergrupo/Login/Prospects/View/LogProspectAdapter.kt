package com.prueba.julianlopez.appintergrupo.Login.Prospects.View

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.AppDatabase
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.LogEntity
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.ProspectEntity
import com.prueba.julianlopez.appintergrupo.Login.Util.ConstantsMessages
import com.prueba.julianlopez.appintergrupo.Login.Util.DataCache
import com.prueba.julianlopez.appintergrupo.Login.Util.ExpandAndCollapseViewUtil
import com.prueba.julianlopez.appintergrupo.R
import kotlinx.android.synthetic.main.log_row.view.*
import kotlinx.android.synthetic.main.prospect_row.view.*
import java.util.*

class LogProspectAdapter (private var context: Context, private var list: List<LogEntity>): RecyclerView.Adapter<LogProspectAdapter.CustomViewHolder>() {



    private var swichChangeIconBtnExpander = true
    private val DURATION = 250
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.log_row, parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val item = list!![position]

        holder.name!!.text = item.name+" "+item.surname
        holder.document!!.text = item.schProspectIdentification
        holder.phone!!.text = item.telephone
        holder.statusCode!!.text = item.statusCd.toString()
        holder.address!!.text = item.address.toString()
        holder.observation!!.text = item.observation.toString()
        holder.previousName!!.text = item.previousName+" "+item.previousSurname
        holder.previousDocument!!.text = item.previousSchProspectIdentification
        holder.previousPhone!!.text = item.previousTelephone
        holder.previousAddress!!.text = item.previousAddress.toString()
        holder.previousObservation!!.text = item.previousRejectedObservation.toString()
        holder.date!!.text = item.date.toString()
        holder.userModifies!!.text = item.userThatModifies.toString()







        when (item.statusCd) {
            0 -> holder.colorStatus!!.background = context.resources.getDrawable(R.color.yellow,null)
            1 -> holder.colorStatus!!.background = context.resources.getDrawable(R.color.red,null)
            2 -> holder.colorStatus!!.background = context.resources.getDrawable(R.color.green,null)
            3 -> holder.colorStatus!!.background = context.resources.getDrawable(R.color.gray,null)
            4 -> holder.colorStatus!!.background = context.resources.getDrawable(R.color.colorPrimary,null)

            else -> { // Note the block
                holder.colorStatus!!.background = context.resources.getDrawable(R.color.white,null)
            }
        }


    }




    override fun getItemCount(): Int {
        return list?.size
    }

    override fun getItemViewType(position: Int): Int {
        return position;
    }

    inner class CustomViewHolder(view: View): RecyclerView.ViewHolder(view){

        var name: TextView? = view.namel
        var document: TextView? = view.documentl
        var phone: TextView? = view.phonel
        var address: TextView? = view.address
        var observation: TextView? = view.observation
        var previousName: TextView? = view.previousName
        var previousDocument: TextView? = view.previousDocument
        var previousPhone: TextView? = view.previousPhone
        var previousAddress: TextView? = view.previousAddress
        var previousObservation: TextView? = view.previousObservation
        var statusCode: TextView? = view.statusCodel
        var colorStatus: LinearLayout? = view.colorStatusl
        var date: TextView? = view.Date
        var userModifies: TextView? = view.userModifies
    }







}