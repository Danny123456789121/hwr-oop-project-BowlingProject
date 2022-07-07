package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class BowlingTest {

    private Game game;
    private PrintScore printer;

    @BeforeEach
    void init() {
        game = new Game();
        printer = new PrintScore(game);
    }

    @Test
    void testInstances() {
        assertThat(game).isInstanceOf(Game.class);
    }

    @Nested
    class GameTests {

        @Test
        void ValidGame_example() {
            game.roll(8);
            game.roll(2);

            game.roll(5);
            game.roll(4);

            game.roll(9);
            game.roll(0);

            rollStrike();

            rollStrike();

            game.roll(5);
            game.roll(5);

            game.roll(5);
            game.roll(3);

            game.roll(6);
            game.roll(3);

            game.roll(9);
            game.roll(1);

            game.roll(9);
            game.roll(1);
            rollStrike();

            assertThat(game.calculateScore()).isEqualTo(149);
            assertThatNoException().isThrownBy(() -> {
                game.calculateScore();
            });
        }

        @Test
        void illegalRoll() {
            game.roll(9);
            game.roll(9);
            assertThatThrownBy(() -> game.calculateScore())
                    .isInstanceOf(RuntimeException.class)
                    .hasMessageContaining("More Pins knocked over than possible");
        }

        @Test
        void isInvalidGame() {
            //ToDo
        }

        @Test
        void calculateScore_isWorstGame_finalScoreIsZero() {
            rollMany(20, 0);
            assertThat(game.calculateScore()).isZero();
        }

        @Test
        void calculateScore_perfectGame_maxPoints() {
            rollMany(12, 10);
            assertThat(game.calculateScore()).isEqualTo(300);
        }

        @Test
        void calculateScore_isStrike_rollscoreIncludesNextTwoRolls() {
            rollStrike();
            game.roll(4);
            game.roll(3);
            rollMany(16, 0);
            assertThat(game.calculateScore()).isEqualTo(24);
        }

        @Test
        void calculateScore_isSpare_rollscoreIncludesNextRoll() {
            rollSpare();
            game.roll(4);
            rollMany(17, 0);
            assertThat(game.calculateScore()).isEqualTo(18);
        }

        @Test
        void calculateScore_SpareFollowedByStrike_rollscore() {
            rollSpare();
            rollStrike();
            game.roll(4);
            rollMany(17, 0);
            assertThat(game.calculateScore()).isEqualTo(38);
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