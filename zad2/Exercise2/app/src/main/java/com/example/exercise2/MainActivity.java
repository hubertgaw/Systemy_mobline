package com.example.exercise2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Intent getWebAdressIntent(String address, String type) {
        Uri uriAdress = Uri.parse(address);
        return new Intent(type, uriAdress);
    }

    private void openActivity(String name, String type) {
        Intent gotoActivity = getWebAdressIntent(name, type);
        if (gotoActivity.resolveActivity(getPackageManager()) != null) {
            startActivity(gotoActivity);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button secondActivityBtn = (Button) findViewById(R.id.secondActivityBtn);
        secondActivityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(),SecondActivity.class);
                startActivity(startIntent);
            }
        });

        Button googleBtn = (Button)findViewById(R.id.googleBtn);
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("http://www.google.com", Intent.ACTION_VIEW);
            }
        });

        Button mapBtn = (Button)findViewById(R.id.mapBtn);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California", Intent.ACTION_VIEW);
            }
        });

        Button phoneBtn = (Button)findViewById(R.id.phoneBtn);
        phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity("tel:5551234", Intent.ACTION_DIAL);
            }
        });
    }

    public void onClickCalculatorBtn(View view) {
        Intent calculatorIntent = new Intent(MainActivity.this, CalculatorActivity.class);
        startActivity(calculatorIntent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}