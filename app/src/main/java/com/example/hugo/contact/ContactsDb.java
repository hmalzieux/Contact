package com.example.hugo.contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by hugo on 09/02/2017.
 */

public class ContactsDb extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final String DATABASE_NAME = "Contact.db";
    public static final String TABLE_NAME = "Contact_table";
    public static final String KEY_ID = "_id";
    public static final String COL_NOM = "NOM";
    public static final String COL_PRENOM = "PRENOM";
    public static final String COL_TELEPHONE = "TELEPHONE";


    public ContactsDb(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ( _id INTEGER PRIMARY KEY, NOM TEXT, PRENOM TEXT, TELEPHONE INTEGER)");
        this.insertData("Dupont","Pierre","0694858875");
        this.insertData("Martin","Roger","0697958275");
        this.insertData("Cotechat","Tibo","0618285249");
        this.insertData("Perbet","Bouebouette","0697958275");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String nom, String prenom, String telephone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NOM, nom);
        contentValues.put(COL_PRENOM, prenom);
        contentValues.put(COL_TELEPHONE, telephone);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }


    public void deleteAllData() {
        this.getWritableDatabase().delete(TABLE_NAME,null,null);
        Log.d("delete data","delete");
    }

    public Cursor getAllRows(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query(TABLE_NAME,new String[] {COL_NOM,COL_NOM,COL_PRENOM},null, null, null, null, null);
        res.close();
        return res;
    }

    public Cursor getPhoneNumber( Long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = KEY_ID + "="+id;
        Cursor c = db.query(true, TABLE_NAME, new String[]{COL_TELEPHONE}, where, null, null, null, null,null);
        if (c != null){
            c.moveToFirst();
        }
        c.close();
        return c;
    }

}
