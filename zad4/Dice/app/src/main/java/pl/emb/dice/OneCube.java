package pl.emb.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OneCube extends AppCompatActivity {

    private Accelerometer accelerometer;
    private TextView mNumber;
    private ImageView cube;
    Tools tools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_cube);

        mNumber = (TextView)findViewById(R.id.number);
        cube = (ImageView)findViewById(R.id.oneCubeImage);
        accelerometer = new Accelerometer(this);
        tools = new Tools();
        accelerometer.setListener(new Accelerometer.Listener() {
            @Override
            public void onShake(float acceleration) {
                if (acceleration > accelerometer.getShakeThreshold()) {
                    int randomNum = tools.generateRandomNumber();
                    mNumber.setText(Integer.toString(randomNum));
                    tools.setImage(cube, randomNum);
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