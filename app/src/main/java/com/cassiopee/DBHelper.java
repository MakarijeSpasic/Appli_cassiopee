package com.cassiopee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Patients", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Patientsdetails(name TEXT, lastname TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists Patientsdetails");
    }

    public Boolean insertPatient(String name, String lastname){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("lastname",lastname);
        long result = DB.insert("Patientsdetails",null, contentValues);
        if (result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean isInDB(String name, String lastname){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Patientsdetails where name = ? and lastname = ?",new String[] {name,lastname});
        if (cursor == null){
            return false;
        } else {
            return true;
        }
    }
}
