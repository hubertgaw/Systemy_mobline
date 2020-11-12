package com.example.exercise3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import static android.view.Window.FEATURE_NO_TITLE;
import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;

public class DrawActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DrawView drawView;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        //requestWindowFeature(FEATURE_NO_TITLE);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        setContentView(R.layout.activity_draw);
        initMetrics();
        initSpinner();
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch ((int) parent.getItemIdAtPosition(position)) {
                    case 0:
                        drawView.normal();
                        break;
                    case 1:
                        drawView.emboss();
                        break;
                    case 2:
                        drawView.blur();
                        break;
                }

        }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void initMetrics() {
        drawView = (DrawView) findViewById(R.id.drawView);
        //drawView.requestFocus();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        drawView.init(metrics);
    }

    private void initSpinner() {
        spinner = (Spinner) findViewById(R.id.spinner_type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }


    public void onClickClearButton(View view) {
        drawView.clear();
    }

    public void onClickRedButton(View view) {
        drawView.changeColorToRed();
    }


    public void onClickBlueButton(View view) {
        drawView.changeColorToBlue();
    }

    public void onClickGreenButton(View view) {
        drawView.changeColorToGreen();
    }

    public void onClickRubber(View view) {
        drawView.normal();
        drawView.changeColorToBackground();
    }

    public void onClickMedium(View view) {
        drawView.mediumThickness();
    }

    public void onClickThin(View view) {
        drawView.thin();
    }

    public void onClickThick(View view) {
        drawView.thick();
    }

    public void onClickUltraThick(View view) {
        drawView.ultraThick();
    }
}