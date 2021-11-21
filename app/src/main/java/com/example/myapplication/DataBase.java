package com.example.myapplication;


import android.content.Context;

import androidx.room.Database;

import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = {Task.class}, version = 1)
public abstract class  DataBase extends RoomDatabase {
    public abstract TaskDAO taskDao();


    private static DataBase db =null;
    public static DataBase getDataBaseObj(Context context){
        if (db==null){
            db = Room.databaseBuilder(context.getApplicationContext(),DataBase.class, "tasks").allowMainThreadQueries().build();
        }
        return db;
    }

}