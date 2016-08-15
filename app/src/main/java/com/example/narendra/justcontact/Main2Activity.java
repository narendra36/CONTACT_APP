package com.example.narendra.justcontact;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.lang.String;

public class Main2Activity extends AppCompatActivity {
    String name,emailid;
    String mobileNo;
    EditText NAME,CONTACT,EMAIL;
    Button BTN;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        NAME = (EditText) findViewById(R.id.name) ;
        CONTACT = (EditText) findViewById(R.id.contact_no);
        EMAIL = (EditText) findViewById(R.id.email_id);
        BTN = (Button) findViewById(R.id.save_data_btn);
        BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = NAME.getText().toString();
                mobileNo = CONTACT.getText().toString();
                emailid = EMAIL.getText().toString();
                databaseOperations dbo = new databaseOperations(ctx);
                Boolean flag=false;

                Log.v("123",name+","+(name==null)+","+name.equals(""));

                if((!name.equals(""))&&(!mobileNo.equals(""))&&(!emailid.equals(""))) {
                    dbo.putInformation(dbo,name,mobileNo,emailid);
                    Intent intent = new Intent();
                    flag=true;
                    intent.putExtra("flag",flag);
                    setResult(RESULT_OK, intent);
                    Log.d("main2actvity ","Data inserted into databses succesfully");
                    Toast.makeText(getBaseContext(),"Data successfully Inserted ",Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Fill up all the information",Toast.LENGTH_LONG).show();
                    NAME.setText("");
                    CONTACT.setText("");
                    EMAIL.setText("");
                }
            }
        });

    }
    /*public void addContact(View v){
        EditText nam = (EditText) findViewById(R.id.name) ;
        name = nam.getText().toString();
        EditText mn = (EditText) findViewById(R.id.contact_no);
        mobileNo = mn.getText().toString();
        EditText emal = (EditText) findViewById(R.id.email_id);
        emailid = emal.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("name",name);
        intent.putExtra("contact",mobileNo);
        intent.putExtra("Email",emailid);
        setResult(RESULT_OK, intent);
        finish();
    }*/
}
