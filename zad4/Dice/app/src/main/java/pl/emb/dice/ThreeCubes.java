package pl.emb.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ThreeCubes extends AppCompatActivity {

    private Accelerometer accelerometer;
    private TextView mNumber1, mNumber2, mNumber3;
    Randomizer randomizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_cubes);

        mNumber1 = (TextView)findViewById(R.id.threeNumbers1);
        mNumber2 = (TextView)findViewById(R.id.threeNumbers2);
        mNumber3 = (TextView)findViewById(R.id.threeNumbers3);
        accelerometer = new Accelerometer(this);
        randomizer = new Randomizer();
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onShake(float acceleration) {
                if (acceleration > accelerometer.getShakeThreshold()) {
//                    Random randomGenerator = new Random();
//                    int randomNum = randomGenerator.nextInt(6) + 1;
                    int randomNum1 = randomizer.generateRandomNumber();
                    mNumber1.setText(Integer.toString(randomNum1));

                    int randomNum2 = randomizer.generateRandomNumber();
                    mNumber2.setText(Integer.toString(randomNum2));

                    int randomNum3 = randomizer.generateRandomNumber();
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