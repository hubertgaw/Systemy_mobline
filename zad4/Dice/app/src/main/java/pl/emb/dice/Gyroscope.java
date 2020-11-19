package pl.emb.dice;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import static android.content.Context.SENSOR_SERVICE;

public class Gyroscope implements SensorEventListener {
    public interface Listener {
        void onRotate(float x, float y, float z);
    }

    private SensorManager mSensorManager;
    private Sensor mGyroscope;
    private final int ROTATION_THRESHOLD = 3;
    private int randomNum;
    private Listener listener;

    public Gyroscope(Context context) {
        mSensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        mGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    public int getRotationThreshold() {
        return ROTATION_THRESHOLD;
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE && listener != null) {
            float x = Math.abs(event.values[0]);
            float y = Math.abs(event.values[1]);
            float z = Math.abs(event.values[2]);

            listener.onRotate(x, y, z);
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void setListener(Gyroscope.Listener l) {
        listener = l;
    }

    public void register() {
        mSensorManager.registerListener(this,
                mGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void unregister() {
        mSensorManager.unregisterListener(this);
    }
}
