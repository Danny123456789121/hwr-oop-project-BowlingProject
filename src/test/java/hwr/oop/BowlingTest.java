package hwr.oop;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {
    Game game = new Game();

    @Nested
    class IsInstanceOfTest {
        @Test
        void testInstances() {
            assertThat(game).isInstanceOf(Game.class);
        }
    }

    @Nested
    class GameTests {
        @Test
        void getWorstGame() {
            rollMany(20,0);
            assertThat(game.score()).isZero();
        }

        @Test
        void isValidGame() {
            rollMany(20,1);
            //ToDo
        }

        @Test
        void isInvalidGame() {
            //ToDo
        }

        @Test
        void getPerfectGame() {
            rollMany(12,10);
            assertThat(game.score()).isEqualTo(300);
        }

        @Test
        void isStrike() {
            rollStrike();
            game.roll(4);
            game.roll(3);
            rollMany(16,0);
            assertThat(game.score()).isEqualTo(24);
        }

        @Test
        void isSpare() {
            rollSpare();
            game.roll(4);
            rollMany(17,0);
            assertThat(game.score()).isEqualTo(18);
        }
    }

    private void rollMany(int n, int pins) {
        for (int i = 0; i < n; i++) {
            game.roll(pins);
        }
    }

    private void rollStrike() {
        game.roll(10);
    }

    private void rollSpare() {
        game.roll(4);
        game.roll(6);
    }
}


//TODO
    /*
Spare, Strike = 10P

    Test - Game
- initAGame
- fullGame
- missedAllThrows == game 0P
- perfectGame == game 300P

    Tests - possible Points
- missedThrow = 0P
- simpleThrow = Roll(x)
- simpleSpare = 10P + nextThrow
- simpleStrike = 10P + nextThrow + nextThrow+1
            - TwoStrikesInARow = 20P + nextThrow+1
            - ThreeStrikesInARowFromFrameOne = 30P
- strikeNextSpare = 10P + 10P (FromNextFrame) (always)
            - SpareNextStrike = 10P + 10P (FromNextThrow)

    Test - LastFrame
- whenSpareOrStrike_AdditionalThrow = 3 Throws
- SpareAndStrike = 20P
- ThreeStrikes = 30P
- TwoNormalThrows = NoThirdThrow


    Test - caculate Frame ?


    Class
- Game
- ScoreFrame
*/