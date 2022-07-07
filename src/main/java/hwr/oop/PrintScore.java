package hwr.oop;

public class PrintScore {

    public void printFrame(int rollOne, int rollTwo, int score) {
        System.out.println(rollOne + " " + rollTwo + " = " + score);
    }

    public void printStrike() {
        System.out.print("X ");
    }

    public void printStrike(int score) {
        System.out.println("X" + " = " + score);
    }

    public void printSpare(int firstSpareNumber) {
        System.out.print(firstSpareNumber + " / ");
    }

    public void printSpare(int firstSpareNumber, int score) {
        System.out.println(firstSpareNumber + " /" + " = " + score);
    }

    public void spacing() {
        System.out.println("------------------------------");
    }
}
