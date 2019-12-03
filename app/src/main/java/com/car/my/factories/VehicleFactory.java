package com.car.my.factories;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.car.my.db.MyCarAppDbHelper;
import com.car.my.entities.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleFactory {

    public static List<Vehicle> getAllFromDataBase(SQLiteDatabase db) {
        Cursor cursor = db.query(MyCarAppDbHelper.VEHICLES_TABLE_NAME, null, null,
                null, null, null, null);
        List<Vehicle> list = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Vehicle v = new Vehicle(cursor.getInt(0), cursor.getString(1)
                        , cursor.getLong(2), cursor.getLong(3));
                list.add(v);

            } while (cursor.moveToNext());
            return list;
        } else
            return new ArrayList<>();
    }
}
