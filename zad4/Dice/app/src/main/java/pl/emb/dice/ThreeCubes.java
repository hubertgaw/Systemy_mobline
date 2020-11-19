package pl.emb.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ThreeCubes extends AppCompatActivity {

    private Accelerometer accelerometer;
    //private TextView mNumber1, mNumber2, mNumber3;
    private ImageView cube1, cube2, cube3;
    Tools tools;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_cubes);

//        mNumber1 = (TextView)findViewById(R.id.threeNumbers1);
//        mNumber2 = (TextView)findViewById(R.id.threeNumbers2);
//        mNumber3 = (TextView)findViewById(R.id.threeNumbers3);

        cube1 = (ImageView)findViewById(R.id.threeCubesImage1);
        cube2 = (ImageView)findViewById(R.id.threeCubesImage2);
        cube3 = (ImageView)findViewById(R.id.threeCubesImage3);

        accelerometer = new Accelerometer(this);
        tools = new Tools();
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onShake(float acceleration) {
                if (acceleration > accelerometer.getShakeThreshold()) {
                    int randomNum1 = tools.generateRandomNumber();
//                    mNumber1.setText(Integer.toString(randomNum1));
                    tools.setImage(cube1,randomNum1);

                    int randomNum2 = tools.generateRandomNumber();
//                    mNumber2.setText(Integer.toString(randomNum2));
                    tools.setImage(cube2,randomNum2);

                    int randomNum3 = tools.generateRandomNumber();
//                    mNumber3.setText(Integer.toString(randomNum3));
                    tools.setImage(cube3,randomNum3);
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