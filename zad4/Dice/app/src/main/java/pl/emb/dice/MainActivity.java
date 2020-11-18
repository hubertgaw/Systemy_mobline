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

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView mNumber;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private static int SHAKE_THRESHOLD = 3;

    public MainActivity() {
    }

    public MainActivity(SensorManager mSensorManager) {
        this.mSensorManager = mSensorManager;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNumber = (TextView) findViewById(R.id.number);
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float acceleration = (float) Math.sqrt(x*x + y*y + z*z) - SensorManager.GRAVITY_EARTH;

        if(acceleration > SHAKE_THRESHOLD) {
            generateRandomNumber();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void generateRandomNumber() {
        Random randomGenerator = new Random();
        int randomNum = randomGenerator.nextInt(6) + 1;
        mNumber.setText(Integer.toString(randomNum));
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