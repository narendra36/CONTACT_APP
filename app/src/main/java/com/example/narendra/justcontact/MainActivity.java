package com.example.narendra.justcontact;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    //ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    //public ArrayAdapter<String> adapter;

    //RECORDING HOW MANY TIMES THE BUTTON HAS BEEN CLICKED
    CustomList adapter;
    ListView list;
    Vector<String> names;
    Vector<String> contacts;
    Vector<String> emails;

    Integer[] imageId = {
            R.drawable.pro1
    };
    Boolean flag=false;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        names=new Vector<>();
        contacts=new Vector<>();
        emails=new Vector<>();
        adapter = new CustomList(MainActivity.this, names,contacts,emails , imageId);
        list=(ListView)findViewById(R.id.list);


        // adapter=new ArrayAdapter<String>(this,R.layout.mytextview, listItems);
        //ListView listview = (ListView) findViewById(R.id.list);
        databaseOperations dbo = new databaseOperations(ctx);
        Cursor CR = dbo.getInformation(dbo);
        CR.moveToFirst();
        if(CR.moveToFirst()) {
             do{
                 names.add(CR.getString(0));
                 contacts.add(CR.getString(1));
                 emails.add(CR.getString(2));

            }while (CR.moveToNext());
        }
        //listview.setAdapter(adapter);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // Toast.makeText(MainActivity.this, "You Clicked at " +web.get(position), Toast.LENGTH_SHORT).show();

            }
        });
        list.setOnItemLongClickListener (new AdapterView.OnItemLongClickListener() {
            @SuppressWarnings("rawtypes")
            public boolean onItemLongClick(AdapterView parent, View view, final int position, long id) {
                final CharSequence[] items = { "Delete" ,"Edit"};

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

                builder.setTitle("Action:");
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int item) {
                        databaseOperations dbo = new databaseOperations(ctx);
                        Cursor CR = dbo.getInformation(dbo);
                        if(item==0){
                            CR.moveToFirst();
                            String name="",cont="",eml="";

                            CR.move(position);
                            name = CR.getString(0);
                            cont = CR.getString(1);
                            eml =  CR.getString(2);

                            Log.d("###",name+" contact : "+cont+" email : "+eml);
                            dbo.deleteElement(dbo,name,cont,eml);
                            names.remove(position);
                            contacts.remove(position);
                            emails.remove(position);
                            adapter.notifyDataSetChanged();

                        }
                        else if(item==1){
                            Log.d("###","I am in item 1");
                            CR.moveToFirst();
                            String name="",cont="",eml="";

                            CR.move(position);
                            name = CR.getString(0);
                            cont = CR.getString(1);
                            eml =  CR.getString(2);
                            Bundle b = new Bundle();
                            Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                            b.putString("name",name);
                            b.putString("contact",cont);
                            b.putString("email",eml);
                            intent.putExtras(b);
                            startActivityForResult(intent, 1);
                        }
                    }

                });

                AlertDialog alert = builder.create();

                alert.show();
                //do your stuff here
                return false;
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* listItems.add("Clicked : "+clickCounter++);
               adapter.notifyDataSetChanged();
                 */
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                flag=data.getBooleanExtra("flag",false);
            }
        }

        if(flag==true)
        {   names.clear();
            contacts.clear();
            emails.clear();
            databaseOperations dbo = new databaseOperations(ctx);
            Cursor CR = dbo.getInformation(dbo);
            CR.moveToFirst();
            do
            {
                names.add(CR.getString(0));
                contacts.add(CR.getString(1));
                emails.add(CR.getString(2));

            }while (CR.moveToNext());
        }
        adapter.notifyDataSetChanged();
        Log.d("Main Activity","List is updated !!");
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
