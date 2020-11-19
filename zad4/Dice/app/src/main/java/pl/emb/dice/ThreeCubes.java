package pl.emb.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ThreeCubes extends AppCompatActivity {

    private Accelerometer accelerometer;
    private TextView mNumber1, mNumber2, mNumber3;
    Tools tools;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_cubes);

        mNumber1 = (TextView)findViewById(R.id.threeNumbers1);
        mNumber2 = (TextView)findViewById(R.id.threeNumbers2);
        mNumber3 = (TextView)findViewById(R.id.threeNumbers3);
        accelerometer = new Accelerometer(this);
        tools = new Tools();
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onShake(float acceleration) {
                if (acceleration > accelerometer.getShakeThreshold()) {
                    int randomNum1 = tools.generateRandomNumber();
                    mNumber1.setText(Integer.toString(randomNum1));

                    int randomNum2 = tools.generateRandomNumber();
                    mNumber2.setText(Integer.toString(randomNum2));

                    int randomNum3 = tools.generateRandomNumber();
                    mNumber3.setText(Integer.toString(randomNum3));
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