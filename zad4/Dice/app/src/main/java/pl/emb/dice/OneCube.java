package pl.emb.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OneCube extends AppCompatActivity {

    private Accelerometer accelerometer;
    private TextView mNumber;
    private List<TextView> numbers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_cube);

        mNumber = (TextView)findViewById(R.id.number);
        accelerometer = new Accelerometer(this);
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onShake(float acceleration) {
                if (acceleration > accelerometer.getShakeThreshold()) {
                    Random randomGenerator = new Random();
                    int randomNum = randomGenerator.nextInt(6) + 1;
                    mNumber.setText(Integer.toString(randomNum));
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        accelerometer.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        accelerometer.unregister();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}