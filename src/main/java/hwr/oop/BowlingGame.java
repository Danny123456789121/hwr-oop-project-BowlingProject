package hwr.oop;

public class BowlingGame implements Game {
    private final PrintScore printer = new PrintScore();
    private final int[] rolls = new int[21];
    private int currentRoll = 0;
    private int frameIndex;

    @Override
    public void roll(int pinAmount) {
        if (pinAmount >= 0 && pinAmount <= 10) {
            rolls[currentRoll++] = pinAmount;
        } else {
            throw new InvalidAmountOfPinsRolled("To many pins rolled");
        }

    }

    @Override
    public int calculateScore() {
        int score = 0;
        frameIndex = 0;
        for (int frame = 0; frame < 10; frame++)
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                printer.printStrike(score);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                printer.printSpare(rolls[frameIndex],score);
                frameIndex += 2;
            } else {
                score += sumOfPinsInFrame(frameIndex);
                printer.printFrame(rolls[frameIndex], rolls[frameIndex + 1],score);
                frameIndex += 2;
            }
        calculateLastFrame();
        return score;
    }

    @Override
    public int sumOfPinsInFrame(int frameIndex) {
        if ((rolls[frameIndex] + rolls[frameIndex + 1]) < 10) {
            return rolls[frameIndex] + rolls[frameIndex + 1];
        }
        throw new InvalidAmountOfPinsRolled("More Pins knocked over than possible");
    }

    //TODO
    private void calculateLastFrame() {
        if ((isStrike(frameIndex - 2) || isSpare(frameIndex - 2)) && rolls[frameIndex] == 10) {
            printer.printStrike();
            if (rolls[frameIndex + 1] == 10) {
                printer.printStrike();
            }
        }
    }

    private boolean isStrike(int frameIndex) {
        return rolls[frameIndex] == 10;
    }

    private boolean isSpare(int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1] == 10;
    }

    private int spareBonus(int frameIndex) {
        return rolls[frameIndex + 2];
    }

    private int strikeBonus(int frameIndex) {
        return rolls[frameIndex + 1] + rolls[frameIndex + 2];
    }
}
