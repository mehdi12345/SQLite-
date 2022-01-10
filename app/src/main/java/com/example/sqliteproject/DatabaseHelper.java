package com.example.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Personne.db";
    public static final String TABLE_NAME="Personne";
    public static final String col_1="ID";
    public static final String col_2="Nom";
    public static final String col_3="Prenom";
    public static final String col_4="Age";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
     sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, Nom TEXT, Prenom TEXT, Age INTEGER );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public boolean insert(String Nom,String Prenom ,Integer Age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(col_2,Nom);
        contentValues.put(col_3,Prenom);
        contentValues.put(col_4,Age);
        long res= db.insert(TABLE_NAME,null,contentValues);
        if(res==-1)
            return false;
        return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean update(String id,String Nom,String Prenom,Integer Age){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(col_1,id);
        contentValues.put(col_2,Nom);
        contentValues.put(col_3,Prenom);
        contentValues.put(col_4,Age);
        db.update(TABLE_NAME,contentValues,"ID=?",new String[] { id });
        return true;
    }
    public Integer Delete(String Id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"ID=?",new String[]{Id});

    }
}
