package pl.emb.dice;

import java.util.Random;

public class Randomizer {
    public int generateRandomNumber() {
        Random randomGenerator = new Random();
        int randomNum = randomGenerator.nextInt(6) + 1;
        return randomNum;
    }
}
