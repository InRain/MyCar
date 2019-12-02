package com.car.my.entities;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.car.my.db.MyCarAppDbHelper;

public class Vehicle {
    private long id;
    private String name;
    private long initialRun;
    private long currentRun;

    public Vehicle(long id, String name, long initialRun, long currentRun){
        this.id = id;
        this.name = name;
        this.initialRun = initialRun;

        this.currentRun = currentRun;
    }

    public String getName() {
        return name;
    }

    public long getCurrentRun() {
        return currentRun;
    }

    public void saveToDatabase(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        values.put(MyCarAppDbHelper.VEHICLES_NAME, this.name);
        values.put(MyCarAppDbHelper.VEHICLES_INITIAL_RUN, this.initialRun);
        values.put(MyCarAppDbHelper.VEHICLES_CURRENT_RUN, this.currentRun);

        db.insert(MyCarAppDbHelper.VEHICLES_TABLE_NAME,null,values);
    }
}
