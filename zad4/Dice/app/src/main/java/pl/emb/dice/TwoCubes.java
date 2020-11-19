package pl.emb.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class TwoCubes extends AppCompatActivity {

    private Accelerometer accelerometer;
    private Gyroscope gyroscope;
//    private TextView mNumber1, mNumber2;
    private ImageView cube1, cube2;
    Tools tools;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_cubes);

//        mNumber1 = (TextView)findViewById(R.id.twoNumbers1);
//        mNumber2 = (TextView)findViewById(R.id.twoNumbers2);
        cube1 = (ImageView)findViewById(R.id.twoCubesImage1);
        cube2 = (ImageView)findViewById(R.id.twoCubesImage2);
        accelerometer = new Accelerometer(this);
        gyroscope = new Gyroscope(this);
        tools = new Tools();
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onShake(float acceleration) {
                if (acceleration > accelerometer.getShakeThreshold()) {
                    int randomNum1 = tools.generateRandomNumber();
//                    mNumber1.setText(Integer.toString(randomNum1));
                    tools.setImage(cube1, randomNum1);

                    int randomNum2 = tools.generateRandomNumber();
//                    mNumber2.setText(Integer.toString(randomNum2));
                    tools.setImage(cube2, randomNum2);
                }
            }
        });

        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotate(float x, float y, float z) {
                if (x > gyroscope.getRotationThreshold() || y > gyroscope.getRotationThreshold() || z > gyroscope.getRotationThreshold()) {
                    int randomNum1 = tools.generateRandomNumber();
                    tools.setImage(cube1, randomNum1);

                    int randomNum2 = tools.generateRandomNumber();
                    tools.setImage(cube2, randomNum2);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        accelerometer.register();
        gyroscope.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        accelerometer.unregister();
        gyroscope.unregister();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}