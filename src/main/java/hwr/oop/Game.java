package hwr.oop;

import java.sql.SQLOutput;
import java.util.Arrays;

public class Game {
    private PrintScore printer = new PrintScore(this);
    private final int[] rolls = new int[21];
    private int currentRoll = 0;
    private int score;
    private int currentScore;
    private int frameIndex;

    public void roll(int pins) {
        if (pins >= 0 && pins <= 10)
            rolls[currentRoll++] = pins;
        //System.out.println(Arrays.toString(rolls));
    }

    public int calculateScore()  {
        score = 0;
        frameIndex = 0;
        for (int frame = 0; frame < 10; frame++)
            if (isStrike(frameIndex)) {
                printer.printStrike();
                score += 10 + strikeBonus(frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                printer.printSpare(rolls[frameIndex]);
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            } else {
                try {
                    System.out.println(rolls[frameIndex]+ " " +rolls[frameIndex + 1]);
                    score += sumOfBallsInFrame(frameIndex);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                frameIndex += 2;
            }
        printLastFrame();
        //System.out.println("frameIndex" + frameIndex);
        return score;
    }

    private void printLastFrame() {
        if (isStrike(frameIndex-2) || isSpare(frameIndex-2)){
            if (rolls[frameIndex] == 10) {
                printer.printStrike();
            } else {
                System.out.println(rolls[frameIndex]);
            }
        }
    }

    private boolean isStrike(int frameIndex) {
        return rolls[frameIndex] == 10;
    }

    private boolean isSpare(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1] == 10;
    }

    private int sumOfBallsInFrame(int frameIndex) {
        if ((rolls[frameIndex] + rolls[frameIndex + 1]) < 10) {
            return rolls[frameIndex] + rolls[frameIndex + 1];
        }
        throw new RuntimeException("More Pins knocked over than possible");
    }

    private int spareBonus(int frameIndex) {

        return rolls[frameIndex + 2];
    }

    private int strikeBonus(int frameIndex) {
        /*
        if(frameIndex/2 == 9) {
            System.out.println(rolls[frameIndex] + "\n" + rolls[frameIndex + 1] + "\n" + rolls[frameIndex + 2]);
        }else{
            printer.printStrike();
        }
         */
        return rolls[frameIndex + 1] + rolls[frameIndex + 2];
    }

    public int[] getRolls() {
        return rolls;
    }
}
