package com.ome.akashsachdeva.ome;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.view.Menu;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SecondActivity extends ActionBarActivity {
    private MyDBHandler mydb;

    TextView name;
    TextView email;
    TextView amount;
    int id_To_Update = 0;
    Toolbar toolbar;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        b1=(Button)findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                if(extras !=null)
                {
                    int Value = extras.getInt("id");
                    if(Value>0){
                        if(mydb.updateContact(id_To_Update, name.getText().toString(), email.getText().toString(), amount.getText().toString())){
                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),com.ome.akashsachdeva.ome.credit_activity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        if(mydb.insertContact(name.getText().toString(), email.getText().toString(), amount.getText().toString())){
                            Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                        }

                        else{
                            Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                        }
                        Intent intent = new Intent(getApplicationContext(),com.ome.akashsachdeva.ome.credit_activity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        name = (TextView) findViewById(R.id.editText);
        email = (TextView) findViewById(R.id.editText3);
        amount = (TextView) findViewById(R.id.editText2);

        mydb = new MyDBHandler(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int Value = extras.getInt("id");

            if (Value > 0) {
                Cursor rs = mydb.getData(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String nam = rs.getString(rs.getColumnIndex(MyDBHandler.COLUMN_NAME));
                String emai = rs.getString(rs.getColumnIndex(MyDBHandler.COLUMN_EMAIL));
                String amo = rs.getString(rs.getColumnIndex(MyDBHandler.COLUMN_AMOUNT));

                if (!rs.isClosed()) {
                    rs.close();
                }
                Button b = (Button) findViewById(R.id.button3);
                b.setVisibility(View.INVISIBLE);

                name.setText((CharSequence) nam);
                name.setFocusable(false);
                name.setClickable(false);



                email.setText((CharSequence) emai);
                email.setFocusable(false);
                email.setClickable(false);



                amount.setText((CharSequence) amo);
                amount.setFocusable(false);
                amount.setClickable(false);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            int Value = extras.getInt("id");
            if (Value > 0) {
                getMenuInflater().inflate(R.menu.display_contact, menu);
            } else {
                getMenuInflater().inflate(R.menu.menu_main, menu);
            }
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.Edit_Item:
                Button b = (Button) findViewById(R.id.button3);
                b.setVisibility(View.VISIBLE);
                name.setEnabled(true);
                name.setFocusableInTouchMode(true);
                name.setClickable(true);



                email.setEnabled(true);
                email.setFocusableInTouchMode(true);
                email.setClickable(true);



                amount.setEnabled(true);
                amount.setFocusableInTouchMode(true);
                amount.setClickable(true);

                return true;
            case R.id.Delete_Item:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.deleteItem)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mydb.deleteContact(id_To_Update);
                                Toast.makeText(getApplicationContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), com.ome.akashsachdeva.ome.credit_activity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                AlertDialog d = builder.create();
                d.setTitle("Are you sure");
                d.show();

                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
   public void test(View view)
    {
        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            int Value = extras.getInt("id");
            if(Value>0){
                if(mydb.updateContact(id_To_Update, name.getText().toString(), email.getText().toString(), amount.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),com.ome.akashsachdeva.ome.credit_activity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                if(mydb.insertContact(name.getText().toString(), email.getText().toString(), amount.getText().toString())){
                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getApplicationContext(), "not done", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(getApplicationContext(),com.ome.akashsachdeva.ome.credit_activity.class);
                startActivity(intent);
            }
        }
    }
}