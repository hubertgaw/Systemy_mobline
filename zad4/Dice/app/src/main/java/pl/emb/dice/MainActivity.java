package pl.emb.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {//implements SensorEventListener {


    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goToOneCube(View view) {
        Intent intent = new Intent(this, OneCube.class);
        startActivity(intent);
    }

    public void goToTwoCubes(View view) {
        Intent intent = new Intent(this, TwoCubes.class);
        startActivity(intent);
    }

    public void goToThreeCubes(View view) {
        Intent intent = new Intent(this, ThreeCubes.class);
        startActivity(intent);
    }
}