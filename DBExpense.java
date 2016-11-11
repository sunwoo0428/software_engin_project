package com.example.sunwo.money_book;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBExpense extends SQLiteOpenHelper {

    public DBExpense(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블을 생성한다.
        // cr)eate table 테이블명 (컬럼명 타입 옵션;
        db.execSQL("CREATE TABLE MONEY_EX( _id INTEGER PRIMARY KEY AUTOINCREMENT, expense INTEGER, category TEXT, year INTEGER, month INTEGER, day INTEGER);");
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void createTable(String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }
    public void insert(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }
    public void update(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }
    public void delete(String _query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }
    public void drop(String _query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(_query);
        db.close();
    }
    public String PrintData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        Cursor cursor = db.rawQuery("select * from MONEY_EX", null);
        while(cursor.moveToNext()) {
            str += cursor.getInt(0)
                    + " : expense "
                    + cursor.getInt(1)
                    + ", category = "
                    + cursor.getString(2)
                    + ", Date = "
                    + cursor.getInt(3)
                    + "/"
                    + cursor.getInt(4)
                    + "/"
                    + cursor.getInt(5)
                    + "\n";
        }
        return str;
    }

    public int GetTotalExpense(){
        SQLiteDatabase db = getReadableDatabase();
        int totalExpense = 0;
        Cursor cursor = db.rawQuery("select expense from MONEY_EX", null);
        while(cursor.moveToNext()){
            totalExpense += cursor.getInt(0);
        }
        return totalExpense;
    }
}