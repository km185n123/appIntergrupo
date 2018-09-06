package com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys




import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by root on 30/11/17.
 */
@SuppressLint("ParcelCreator")
@Parcelize
@Entity(tableName = "table_prospect")

class ProspectEntity(

        @ColumnInfo(name = "id")
        var id: String? = null,
        @ColumnInfo(name = "name")
        var name: String? = null,
        @ColumnInfo(name = "surname")
        var surname: String? = null,
        @ColumnInfo(name = "telephone")
        var telephone: String? = null,
        @ColumnInfo(name = "schProspectIdentification")
        var schProspectIdentification: String? = null,
        @ColumnInfo(name = "address")
        var address: String? = null,
        @ColumnInfo(name = "createdAt")
        var createdAt: String? = null,
        @ColumnInfo(name = "updatedAt")
        var updatedAt: String? = null,
        @ColumnInfo(name = "statusCd")
        var statusCd: Int = 0,
        @ColumnInfo(name = "zoneCode")
        var zoneCode: String? = null,
        @ColumnInfo(name = "neighborhoodCode")
        var neighborhoodCode: String? = null,
        @ColumnInfo(name = "cityCode")
        var cityCode: String? = null,
        @ColumnInfo(name = "sectionCode")
        var sectionCode: String? = null,
        @ColumnInfo(name = "roleId")
        var roleId: Int = 0,
        @ColumnInfo(name = "appointableId")
        var appointableId: String? = null,
        @ColumnInfo(name = "rejectedObservation")
        var rejectedObservation: String? = null,
        @ColumnInfo(name = "observation")
        var observation: String? = null,
        @ColumnInfo(name = "isDisable")
        var isDisable: Boolean = false,
        @ColumnInfo(name = "isVisited")
        var isVisited: Boolean = false,
        @ColumnInfo(name = "isCallcenter")
        var isCallcenter: Boolean = false,
        @ColumnInfo(name = "isAcceptSearch")
        var isAcceptSearch: Boolean = false,
        @ColumnInfo(name = "campaignCode")
        var campaignCode: String? = null,
        @ColumnInfo(name = "userId")
        var userId: String? = null,
        @ColumnInfo(name = "updated")
        var updated:Int? = 0





): Parcelable {

    @PrimaryKey(autoGenerate = true)
    var primaryKey :Int? = null

        override fun toString(): String {
                return "ProspectEntity(id=$id, name=$name, surname=$surname, telephone=$telephone, schProspectIdentification=$schProspectIdentification, address=$address, createdAt=$createdAt, updatedAt=$updatedAt, statusCd=$statusCd, zoneCode=$zoneCode, neighborhoodCode=$neighborhoodCode, cityCode=$cityCode, sectionCode=$sectionCode, roleId=$roleId, appointableId=$appointableId, rejectedObservation=$rejectedObservation, observation=$observation, isDisable=$isDisable, isVisited=$isVisited, isCallcenter=$isCallcenter, isAcceptSearch=$isAcceptSearch, campaignCode=$campaignCode, userId=$userId, updated=$updated, primaryKey=$primaryKey)"
        }


}


