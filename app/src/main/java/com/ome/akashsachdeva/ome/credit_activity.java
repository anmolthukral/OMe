package com.ome.akashsachdeva.ome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;

import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class credit_activity extends ActionBarActivity {

    ImageButton FAB;
    private ListView obj;
    MyDBHandler mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        FAB = (ImageButton) findViewById(R.id.creditadd);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(),"Button clicked",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(credit_activity.this, SecondActivity.class);
                startActivity(i);


            }
        });
        mydb = new MyDBHandler(this);
        ArrayList array_list = mydb.getAllData();
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1, array_list);

        obj = (ListView)findViewById(R.id.listView1);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(getApplicationContext(),com.ome.akashsachdeva.ome.SecondActivity.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




}