package com.example.narendra.justcontact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by narendra on 8/12/16.
 */
public class databaseOperations extends SQLiteOpenHelper {
    public static final int database_version = 1;
    public static String CREATE_QUERY = "CREATE TABLE "+ TableData.TableInfo.TABLE_NAME+" ( "+ TableData.TableInfo.USER_NAME+" TEXT, "+ TableData.TableInfo.CONTACT_NO+" TEXT, "+ TableData.TableInfo.EMAIL_ID+" TEXT);";
    public databaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("databases operations ","Databases Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb){
        sdb.execSQL(CREATE_QUERY);
        Log.d("databases operations ","Table is created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1,int arg2){

    }

    public void putInformation(databaseOperations db,String name,String contactNo ,String email){
        SQLiteDatabase DB = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME,name);
        cv.put(TableData.TableInfo.CONTACT_NO,contactNo);
        cv.put(TableData.TableInfo.EMAIL_ID,email);
        DB.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("databses operations ","single row inserted");
    }
    public Cursor getInformation(databaseOperations dbo){
        SQLiteDatabase Db = dbo.getReadableDatabase();
        String [] columns = {TableData.TableInfo.USER_NAME, TableData.TableInfo.CONTACT_NO, TableData.TableInfo.EMAIL_ID};
        Cursor CR =Db.query(TableData.TableInfo.TABLE_NAME,columns,null,null,null,null,null);
        return CR;
    }
}
