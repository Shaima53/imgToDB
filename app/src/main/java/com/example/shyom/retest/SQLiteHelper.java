package com.example.shyom.retest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "picDB.db";
    public static final String TABLE_NAME = "PIC.db";

    public SQLiteHelper( Context context,String  DATABASE_NAME ) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void queryData(String sql){
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(sql);
    }

    public void insertData(byte[] image){
        SQLiteDatabase database=getWritableDatabase();
        String sql ="INSERT INTO PIC (pic) VALUES(?) ";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.bindBlob(1,image);
        statement.executeInsert();
    }















    public Cursor getData( ){
        SQLiteDatabase database=getReadableDatabase();


        return database.rawQuery("SELECT pic FROM  PIC",null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
