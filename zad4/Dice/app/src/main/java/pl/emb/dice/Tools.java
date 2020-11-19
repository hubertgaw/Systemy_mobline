package pl.emb.dice;

import android.widget.ImageView;

import java.util.Random;

public class Tools {
    public int generateRandomNumber() {
        Random randomGenerator = new Random();
        int randomNum = randomGenerator.nextInt(6) + 1;
        return randomNum;
    }

    public void setImage(ImageView cube, int number) {
        if (number == 1) {
            cube.setImageResource(R.drawable.dice1);
        } else if (number == 2) {
            cube.setImageResource(R.drawable.dice2);
        } else if (number == 3) {
            cube.setImageResource(R.drawable.dice3);
        } else if (number == 4) {
            cube.setImageResource(R.drawable.dice4);
        } else if (number == 5) {
            cube.setImageResource(R.drawable.dice5);
        } else if (number == 6) {
            cube.setImageResource(R.drawable.dice6);
        }

    }
}
