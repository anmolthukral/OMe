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
    public static final String DATABASE_NAME = "ome.db";
    public static final String TABLE_NAME = "userdata";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_AMOUNT = "amount";
    //public static final String CONTACTS_COLUMN_CITY = "place";
   // public static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;

    public MyDBHandler(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table userdata " +
                        "(id integer primary key, name text,email text,amount integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS userdata");
        onCreate(db);
    }

    public boolean insertContact  (String name, String email,String amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
      //  contentValues.put("phone", phone);
        contentValues.put("email", email);
        //contentValues.put("street", street);
        contentValues.put("amount", amount);
        db.insert("userdata", null, contentValues);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from userdata where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String email,String amount)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        //contentValues.put("phone", phone);
        contentValues.put("email", email);
        //contentValues.put("street", street);
        contentValues.put("amount", amount);
        db.update("userdata", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("userdata",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllData()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from userdata", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
