package hwr.oop;

public class PrintScore {
    private Game game;

    public PrintScore(Game game) {
        this.game = game;
    }

    public void printRoll(){
        System.out.println();
    }

    public void printStrike(){
        System.out.println("X");
    }

    public void printSpare(int n){
        System.out.println(n + " /");
    }

}
