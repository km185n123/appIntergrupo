package com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Dao

import android.arch.persistence.room.*
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.ProspectEntity

@Dao
interface ProspectDao {

    @Query("SELECT * FROM table_prospect WHERE updated <> 0 ")
    fun getLog(): List<ProspectEntity>

    @Query("SELECT * FROM table_prospect ")
    fun getAll(): List<ProspectEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg rospect: ProspectEntity)


    @Delete
    fun delete(prospect: ProspectEntity)


    @Query("SELECT * FROM table_prospect WHERE id = (SELECT MAX(ID) FROM table_prospect); ")
    fun getLastId():Int


    @Query("DELETE FROM table_prospect")
    fun nukeTable()

}