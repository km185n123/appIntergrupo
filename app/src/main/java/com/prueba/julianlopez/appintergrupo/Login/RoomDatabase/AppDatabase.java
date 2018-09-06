package com.prueba.julianlopez.appintergrupo.Login.RoomDatabase;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Dao.LogDao;
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Dao.ProspectDao;
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.LogEntity;
import com.prueba.julianlopez.appintergrupo.Login.RoomDatabase.Entitys.ProspectEntity;

@Database(entities = {ProspectEntity.class, LogEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    private static AppDatabase INSTANCE;

    public abstract ProspectDao ProspectDao();
    public abstract LogDao LogDao();


    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "internal-database")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            // .addMigrations(MIGRATION_1_2)
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


}

