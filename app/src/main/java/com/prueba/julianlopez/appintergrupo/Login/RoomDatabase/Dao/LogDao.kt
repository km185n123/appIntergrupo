package com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Dao

import android.arch.persistence.room.*
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.LogEntity
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.ProspectEntity

@Dao
interface LogDao {



    @Query("SELECT * FROM table_logs ")
    fun getAll(): List<LogEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg rospect: LogEntity)


    @Delete
    fun delete(prospect: LogEntity)


    @Query("SELECT * FROM table_logs WHERE id = (SELECT MAX(ID) FROM table_logs); ")
    fun getLastId():Int


    @Query("DELETE FROM table_logs")
    fun nukeTable()

}