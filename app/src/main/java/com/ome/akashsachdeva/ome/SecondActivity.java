package com.ome.akashsachdeva.ome;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
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
    TextView number;
    int id_To_Update = 0;
    Toolbar toolbar;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_credit);

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
                        if(mydb.updateContact(id_To_Update, name.getText().toString(), email.getText().toString(), amount.getText().toString(),number.getText().toString())){
                            Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),com.ome.akashsachdeva.ome.credit_activity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "not Updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        if(mydb.insertContact(name.getText().toString(), email.getText().toString(), amount.getText().toString(),number.getText().toString())){
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

        name = (TextView) findViewById(R.id.name);
        email = (TextView) findViewById(R.id.email);
        amount = (TextView) findViewById(R.id.amount);
        number = (TextView) findViewById(R.id.mobile);

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
                String num = rs.getString(rs.getColumnIndex(MyDBHandler.COLUMN_NUMBER));

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

                number.setText((CharSequence) num);
                number.setFocusable(false);
                number.setClickable(false);
            }
        }
    }
    public void callme1(View v)
    {
        Intent i= new Intent();
        i.setData(Uri.parse("tel:" + mydb.getnum(id_To_Update)));
        i.setAction(Intent.ACTION_CALL);
        startActivity(i);
    }
    public void smsme1(View v) {
        Uri uri = Uri.parse("smsto:"+mydb.getnum(id_To_Update));
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", "Dear " + mydb.getname(id_To_Update) + ", I will be returning your " + mydb.getamount(id_To_Update) + " bucks ASAP!");
        startActivity(it);
    }

    public void emailme1(View v){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, mydb.getemail(id_To_Update));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Returning your money");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Dear " + mydb.getname(id_To_Update) + ", I will be returning your " + mydb.getamount(id_To_Update) + " bucks ASAP!");
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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

                number.setEnabled(true);
                number.setFocusableInTouchMode(true);
                number.setClickable(true);

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
    @Override
    public void onBackPressed()
    {
        finish();
    }
}