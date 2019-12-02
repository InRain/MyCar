package com.car.my.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyCarAppDbHelper extends SQLiteOpenHelper implements BaseColumns {

    public static final String DB_MYCARDB = "mycar.db";

    //Table Vehicles=================================================
    public static final String VEHICLES_TABLE_NAME = "vehicles";

    public static final String VEHICLES_NAME = "name";
    public static final String VEHICLES_INITIAL_RUN = "initial_run";
    public static final String VEHICLES_CURRENT_RUN = "current_run";
    //================================================================


    private Context c;
    public MyCarAppDbHelper(Context c){
        super(c, DB_MYCARDB,null,1);
        this.c=c;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String statement = "CREATE TABLE " + VEHICLES_TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + VEHICLES_NAME + " TEXT, "
                + VEHICLES_INITIAL_RUN + " TEXT, "
                +VEHICLES_CURRENT_RUN + " TEXT);";
        db.execSQL(statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
