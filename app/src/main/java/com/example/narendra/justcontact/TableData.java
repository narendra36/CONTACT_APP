package com.example.narendra.justcontact;

import android.provider.BaseColumns;

/**
 * Created by narendra on 8/12/16.
 */
public class TableData {

    public TableData(){

    }

    public  static abstract class  TableInfo implements BaseColumns {
        public static final String USER_NAME = "user_name";
        public static final String CONTACT_NO = "contact_no";
        public static final String EMAIL_ID = "email_id";
        public static final String DATABASE_NAME = "mydatabase";
        public static final String TABLE_NAME = "user_info";
        public static final String PROFILE_PATH = "profile_path";
    }
}
