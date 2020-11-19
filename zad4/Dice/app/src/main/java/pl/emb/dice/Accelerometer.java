package pl.emb.dice;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import static android.content.Context.SENSOR_SERVICE;

public class Accelerometer implements SensorEventListener {
    public interface Listener {
        void onShake(float acceleration);
    }

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private final int SHAKE_THRESHOLD = 1;

    public int getShakeThreshold() {
        return SHAKE_THRESHOLD;
    }

    private int randomNum;
    private Listener listener;

    public Accelerometer(Context context) {
        mSensorManager = (SensorManager)context.getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    public int getRandomNum() {
        return randomNum;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER && listener != null) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float acceleration = (float) Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;
            listener.onShake(acceleration);
        }
//        if(acceleration > SHAKE_THRESHOLD) {
//            generateRandomNumber();
//        }
    }

    public void setListener(Listener l)
    {
        listener = l;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private void generateRandomNumber(List<TextView> numbers) {
        Random randomGenerator = new Random();
        randomNum = randomGenerator.nextInt(6) + 1;
        //mNumber.setText(Integer.toString(randomNum));
        for (int i = 0; i < numbers.size(); i++) {
            numbers.get(i).setText(Integer.toString(randomNum));
        }
    }


    public void register() {
        mSensorManager.registerListener(this,
                mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregister() {
        mSensorManager.unregisterListener(this);
    }

}
