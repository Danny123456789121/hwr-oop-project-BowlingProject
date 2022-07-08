package hwr.oop;

public class KegelGame implements Game {

    private final PrintScore printer = new PrintScore();
    private final int[] rolls = new int[16];
    private int currentRoll = 0;

    @Override
    public void roll(int pinAmount) {
        if (pinAmount >= 0 && pinAmount <= 9) {
            rolls[currentRoll++] = pinAmount;
        } else {
            throw new InvalidAmountOfPinsRolled("To many pins rolled");
        }
    }

    @Override
    public int calculateScore() {
        int score = 0;
        int frameIndex = 0;
        for (int frame = 1; frame < 9; frame++)
            if (frame % 2 == 0) {
                if (sumOfPinsInFrame(frameIndex) == 0) {
                    score -= 9;
                    printer.printFrame(rolls[frameIndex], rolls[frameIndex + 1], score);
                } else if (isStrike(frameIndex)) {
                    score -= 9;
                    printer.printStrike(score);
                    frameIndex--;
                } else {
                    score -= sumOfPinsInFrame(frameIndex);
                    printer.printFrame(rolls[frameIndex], rolls[frameIndex + 1], score);
                }
                frameIndex += 2;
            } else {
                if (isStrike(frameIndex)) {
                    score += 9;
                    printer.printStrike(score);
                    frameIndex++;
                } else {
                    score += sumOfPinsInFrame(frameIndex);
                    printer.printFrame(rolls[frameIndex], rolls[frameIndex + 1], score);
                    frameIndex += 2;
                }
            }
        printer.printSpacing();
        return score;
    }

    @Override
    public int sumOfPinsInFrame(int frameIndex) {
        if ((rolls[frameIndex] + rolls[frameIndex + 1]) <= 9) {
            return rolls[frameIndex] + rolls[frameIndex + 1];
        }
        throw new InvalidAmountOfPinsRolled("More Pins knocked over than possible");
    }

    private boolean isStrike(int frameIndex) {
        return rolls[frameIndex] == 9;
    }
}
