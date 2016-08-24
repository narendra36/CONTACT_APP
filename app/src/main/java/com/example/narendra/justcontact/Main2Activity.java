package com.example.narendra.justcontact;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.lang.String;

public class Main2Activity extends AppCompatActivity {
    String name,emailid;
    String mobileNo;
    String oldname,oldcontact,oldemail;
    EditText NAME,CONTACT,EMAIL;
    Button BTN;
    Bundle b;
    Intent i;
    Context ctx = this;
    private static int RESULT_LOAD_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        NAME = (EditText) findViewById(R.id.name) ;
        CONTACT = (EditText) findViewById(R.id.contact_no);
        EMAIL = (EditText) findViewById(R.id.email_id);
        BTN = (Button) findViewById(R.id.save_data_btn);
        i=getIntent();
        b=i.getExtras();
        if(b!=null)
        {
            oldname= b.getString("name");
            oldcontact= b.getString("contact");
            oldemail= b.getString("email");
            NAME.setText(oldname);
            CONTACT.setText(oldcontact);
            EMAIL.setText(oldemail);
        }
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
                    if(b!=null)
                    {
                        dbo.updateInfo(dbo,oldname,oldcontact,oldemail,name,mobileNo,emailid);
                    }
                    else {
                        dbo.putInformation(dbo, name, mobileNo, emailid);
                    }
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

        Button buttonLoadImage = (Button) findViewById(R.id.buttonLoadPicture);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent i = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            ImageView imageView = (ImageView) findViewById(R.id.imgView);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            Log.d("Image set in progress "," I am doing well ");
        }
    }


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
