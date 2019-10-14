package com.example.mad_abc_clothing.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {


    private static final String DATABSE_NAME = "abcclothing.db";
    public DBHandler(@Nullable Context context) {
        super(context, DATABSE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String CREATE_ENTRIES_SQL = "CREATE TABLE "+EmployeeInfo.EmployeeDetails.TABLE_NAME +" ( "+
                EmployeeInfo.EmployeeDetails._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_NAME + " TEXT, "+
                EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_TELEPHONE + " INTEGER,  "+
                EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_GENDER + " TEXT, "+
                EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_TYPE + " TEXT)";




        sqLiteDatabase.execSQL(CREATE_ENTRIES_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean addEmployee(String Name, int PhoneNumber, String Gender, String Type){
        SQLiteDatabase db= getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_NAME,Name);
        values.put(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_TELEPHONE,PhoneNumber);
        values.put(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_GENDER,Gender);
        values.put(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_TYPE,Type);

        long id = db.insert(EmployeeInfo.EmployeeDetails.TABLE_NAME,
                null,
                values);

        if(id<0){
            return false;
        }else{
            return true;
        }

       /* return (id>=0);*/


    }



    public boolean updateEmployee(int Id, String Name, int PhoneNumber, String Gender, String Type){
        SQLiteDatabase db= getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_NAME,Name);
        values.put(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_TELEPHONE,PhoneNumber);
        values.put(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_GENDER,Gender);
        values.put(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_TYPE,Type);


        int count = db.update(EmployeeInfo.EmployeeDetails.TABLE_NAME,values,
                EmployeeInfo.EmployeeDetails._ID + " = ?",
                new String[]{Integer.toString(Id)});


        if(count < 1 ){
            return false;
        }else{
            return true;
        }



    }


    public List searchEmployee(){
        SQLiteDatabase db = getReadableDatabase();


        Cursor cursor = db.query(EmployeeInfo.EmployeeDetails.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                EmployeeInfo.EmployeeDetails._ID + " ASC");

        List<User> list = new ArrayList();

        while (cursor.moveToNext()){
            User user = new User();
            user.setID(cursor.getInt(cursor.getColumnIndexOrThrow(EmployeeInfo.EmployeeDetails._ID)));
            user.setName(cursor.getString(cursor.getColumnIndexOrThrow(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_NAME)));
            user.setTelephone(cursor.getInt(cursor.getColumnIndexOrThrow(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_TELEPHONE)));
            user.setGender(cursor.getString(cursor.getColumnIndexOrThrow(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_GENDER)));
            user.setType(cursor.getString(cursor.getColumnIndexOrThrow(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_TYPE)));
            list.add(user);
        }

        return list;

    }


    public List<User> searchEmployee(String columnName, String parameter){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = null;
        if(TextUtils.equals(columnName, "empID")) {
             cursor = db.query(EmployeeInfo.EmployeeDetails.TABLE_NAME,
                    null,
                    EmployeeInfo.EmployeeDetails._ID + " = ? ",
                    new String[]{parameter},
                    null,
                    null,
                    EmployeeInfo.EmployeeDetails._ID + " ASC");
        }else{
             cursor = db.query(EmployeeInfo.EmployeeDetails.TABLE_NAME,
                    null,
                    EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_TYPE + " =? ",
                    new String[]{parameter},
                    null,
                    null,
                    EmployeeInfo.EmployeeDetails._ID + " ASC");
        }
        List<User> list = new ArrayList();

        while (cursor.moveToNext()){

            User user = new User();
            user.setID(cursor.getInt(cursor.getColumnIndexOrThrow(EmployeeInfo.EmployeeDetails._ID)));
            user.setName(cursor.getString(cursor.getColumnIndexOrThrow(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_NAME)));
            user.setTelephone(cursor.getInt(cursor.getColumnIndexOrThrow(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_TELEPHONE)));
            user.setGender(cursor.getString(cursor.getColumnIndexOrThrow(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_GENDER)));
            user.setType(cursor.getString(cursor.getColumnIndexOrThrow(EmployeeInfo.EmployeeDetails.COLUMN_NAME_EMP_TYPE)));

            list.add(user);
        }

        return list;
    }



}
