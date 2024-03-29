package com.ome.akashsachdeva.ome;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class MyDBHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME = "ome1.db";
    public static final String TABLE_NAME = "userdata1";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_NUMBER = "number";
    private HashMap hp;
    //boolean val;
    public MyDBHandler(Context context)

    {
        super(context, DATABASE_NAME , null, 1);
       // this.val=val;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
       /* if(val ==true){
            db.execSQL(
                    "create table userdata2 " +
                            "(id integer primary key, name text,email text,amount integer)");     }
        else{*/
            db.execSQL(
                    "create table userdata1 " +
                            "(id integer primary key, name text,email text,amount integer,number integer)"
            );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS userdata1");
        onCreate(db);
    }

    public boolean insertContact  (String name, String email,String amount,String number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("amount", amount);
        contentValues.put("number", number);
        db.insert("userdata1", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from userdata1 where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String email,String amount,String number)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("amount", amount);
        contentValues.put("number", number);
        db.update("userdata1", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("userdata1",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllData()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from userdata1", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public Integer dbtostr(){
        int dat =0;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT SUM(amount) FROM userdata1", null);
        if(cursor.moveToFirst()) {
            dat = cursor.getInt(0);
        }

        return dat;
    }
    public Long getnum(int id){
        Long dat=null;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT number FROM userdata1 where id="+id+"", null);
        if(cursor.moveToFirst()) {
            dat = cursor.getLong(0);
        }

        return dat;
    }
    public Integer getamount(int id){
        int dat =0;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT amount FROM userdata1 where id="+id+"", null);
        if(cursor.moveToFirst()) {
            dat = cursor.getInt(0);
        }

        return dat;
    }
    public String getname(int id){
        String dat ="";
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT name FROM userdata1 where id="+id+"", null);
        if(cursor.moveToFirst()) {
            dat = cursor.getString(0);
        }

        return dat;
    }
    public String getemail(int id){
        String dat ="";
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT email FROM userdata1 where id="+id+"", null);
        if(cursor.moveToFirst()) {
            dat = cursor.getString(0);
        }

        return dat;
    }

}
