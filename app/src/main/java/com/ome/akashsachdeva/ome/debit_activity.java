package com.ome.akashsachdeva.ome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
//vasooli
/**
 * Created by AkashSachdeva on 17-07-2015.
 */
public class debit_activity extends ActionBarActivity {
    ImageButton FAB;
    MyDBHandler_debit mydb;
    private ListView obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);

        FAB = (ImageButton) findViewById(R.id.debitadd);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", 0);
                //Toast.makeText(getApplicationContext(),"Button clicked",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(debit_activity.this, SecondActivity_debit.class);
                i.putExtras(dataBundle);
                startActivity(i);
            }
        });
        mydb = new MyDBHandler_debit(this);
        ArrayList array_list = mydb.getAllData();
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array_list);
        obj = (ListView) findViewById(R.id.listView2);
        obj.setAdapter(arrayAdapter);
        obj.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub
                int id_To_Search = arg2 + 1;

                Bundle dataBundle = new Bundle();
                dataBundle.putInt("id", id_To_Search);

                Intent intent = new Intent(com.ome.akashsachdeva.ome.debit_activity.this, com.ome.akashsachdeva.ome.SecondActivity_debit.class);

                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/
   @Override
   public void onBackPressed()
   {
       finish();
   }
}