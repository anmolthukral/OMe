package com.ome.akashsachdeva.ome;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by AkashSachdeva on 17-07-2015.
 */
public class debit_activity extends Activity {
   // ImageButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);

        /*FAB = (ImageButton) findViewById(R.id.debitadd);
        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(getApplicationContext(),"Button clicked",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(debit_activity.this, SecondActivity.class);
                startActivity(i);
            }
        });*/
    }

}