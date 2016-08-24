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
    public static String CREATE_QUERY = "CREATE TABLE "+ TableData.TableInfo.TABLE_NAME+" ( "+ TableData.TableInfo.USER_NAME+" TEXT, "+ TableData.TableInfo.CONTACT_NO+" TEXT, "+ TableData.TableInfo.EMAIL_ID+" TEXT, "+ TableData.TableInfo.PROFILE_PATH+" TEXT);";
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

    public void putInformation(databaseOperations db,String name,String contactNo ,String email,String propath){
        SQLiteDatabase DB = db.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME,name);
        cv.put(TableData.TableInfo.CONTACT_NO,contactNo);
        cv.put(TableData.TableInfo.EMAIL_ID,email);
        cv.put(TableData.TableInfo.PROFILE_PATH,propath);
        DB.insert(TableData.TableInfo.TABLE_NAME,null,cv);
        Log.d("databses operations ","single row inserted");
    }
    public Cursor getInformation(databaseOperations dbo){

        SQLiteDatabase Db = dbo.getReadableDatabase();
        String [] columns = {TableData.TableInfo.USER_NAME, TableData.TableInfo.CONTACT_NO, TableData.TableInfo.EMAIL_ID,TableData.TableInfo.PROFILE_PATH};
        Cursor CR =Db.query(TableData.TableInfo.TABLE_NAME,columns,null,null,null,null,null);
        return CR;
    }
    public void deleteElement(databaseOperations db,String name,String contactNo ,String email,String propath){
        SQLiteDatabase DB = db.getWritableDatabase();
        String selection = TableData.TableInfo.USER_NAME+" LIKE ? AND "+TableData.TableInfo.CONTACT_NO+" LIKE ? AND "+TableData.TableInfo.EMAIL_ID+" LIKE ? AND "+TableData.TableInfo.PROFILE_PATH+" LIKE ?";
        String args[] = {name,contactNo,email,propath};
        DB.delete(TableData.TableInfo.TABLE_NAME,selection,args);
        Log.d("databses operations ","single row deleted");
    }

    public void updateInfo(databaseOperations db,String name,String contact,String email,String oldpath,String Nname,String Ncontact,String Nemail, String Npropath)
    {
        SQLiteDatabase DB = db.getWritableDatabase();
        String selection = TableData.TableInfo.USER_NAME+" LIKE ? AND "+TableData.TableInfo.CONTACT_NO+" LIKE ? AND "+TableData.TableInfo.EMAIL_ID+" LIKE ? AND "+ TableData.TableInfo.PROFILE_PATH+" LIKE ?";
        String args[] = {name,contact ,email,oldpath};
        ContentValues cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME,Nname);
        cv.put(TableData.TableInfo.CONTACT_NO,Ncontact);
        cv.put(TableData.TableInfo.EMAIL_ID,Nemail);
        cv.put(TableData.TableInfo.PROFILE_PATH,Npropath);
        DB.update(TableData.TableInfo.TABLE_NAME,cv,selection,args);
        Log.d("databses operations ","single row has been updated");
    }
}
