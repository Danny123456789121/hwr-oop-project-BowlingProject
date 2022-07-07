package hwr.oop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class GameTest {

    private Game bowlingGame;
    private Game kegelGame;

    @BeforeEach
    void init() {
        bowlingGame = new BowlingGame();
        kegelGame = new KegelGame();
    }

    @Test
    void testInstances() {
        assertThat(bowlingGame).isInstanceOf(Game.class);
        assertThat(kegelGame).isInstanceOf(Game.class);
    }

    private void rollMany(int n, int pins, Game gameType) {
        for (int i = 0; i < n; i++) {
            gameType.roll(pins);
        }
    }

    @Nested
    class BowlingBowlingGameTests {
        @Test
        void ValidGame_example() {
            bowlingGame.roll(8);
            bowlingGame.roll(2);

            bowlingGame.roll(5);
            bowlingGame.roll(4);

            bowlingGame.roll(9);
            bowlingGame.roll(0);

            rollStrike();

            rollStrike();

            bowlingGame.roll(5);
            bowlingGame.roll(5);

            bowlingGame.roll(5);
            bowlingGame.roll(3);

            bowlingGame.roll(6);
            bowlingGame.roll(3);

            bowlingGame.roll(9);
            bowlingGame.roll(1);

            bowlingGame.roll(9);
            bowlingGame.roll(1);
            rollStrike();

            assertThat(bowlingGame.calculateScore()).isEqualTo(149);
            assertThatNoException().isThrownBy(() -> {
                bowlingGame.calculateScore();
            });
        }

        @Test
        void testLastFrame() {
            rollMany(18, 0, bowlingGame);
            bowlingGame.roll(4);
            bowlingGame.roll(5);
            assertThat(bowlingGame.calculateScore()).isEqualTo(9);
        }

        @Test
        void illegalFrameScore() {
            bowlingGame.roll(9);
            bowlingGame.roll(9);
            assertThatThrownBy(() -> bowlingGame.calculateScore()).isInstanceOf(RuntimeException.class).hasMessageContaining("More Pins knocked over than possible");
        }

        @Test
        void illegalRoll() {
            assertThatThrownBy(() -> bowlingGame.roll(15)).isInstanceOf(RuntimeException.class).hasMessageContaining("To many pins rolled");
        }

        @Test
        void calculateScore_isWorstGame_finalScoreIsZero() {
            rollMany(20, 0, bowlingGame);
            assertThat(bowlingGame.calculateScore()).isZero();
        }

        @Test
        void calculateScore_perfectGame_maxPoints() {
            rollMany(12, 10, bowlingGame);
            assertThat(bowlingGame.calculateScore()).isEqualTo(300);
        }

        @Test
        void calculateScore_isStrike_rollscoreIncludesNextTwoRolls() {
            rollStrike();
            bowlingGame.roll(4);
            bowlingGame.roll(3);
            rollMany(16, 0, bowlingGame);
            assertThat(bowlingGame.calculateScore()).isEqualTo(24);
        }

        @Test
        void calculateScore_isSpare_rollscoreIncludesNextRoll() {
            rollSpare();
            bowlingGame.roll(4);
            rollMany(17, 0, bowlingGame);
            assertThat(bowlingGame.calculateScore()).isEqualTo(18);
        }

        @Test
        void calculateScore_SpareFollowedByStrike_rollscore() {
            rollSpare();
            rollStrike();
            bowlingGame.roll(4);
            rollMany(17, 0, bowlingGame);
            assertThat(bowlingGame.calculateScore()).isEqualTo(38);
        }

        private void rollStrike() {
            bowlingGame.roll(10);
        }

        private void rollSpare() {
            bowlingGame.roll(4);
            bowlingGame.roll(6);
        }
    }

    @Nested
    class KegelGameTests {

        @Test
        void ValidGame_example() {
            kegelGame.roll(4);
            kegelGame.roll(2);

            kegelGame.roll(0);
            kegelGame.roll(0);

            kegelGame.roll(8);
            kegelGame.roll(0);

            kegelGame.roll(2);
            kegelGame.roll(5);

            kegelGame.roll(2);
            kegelGame.roll(3);

            kegelGame.roll(6);
            kegelGame.roll(1);

            kegelGame.roll(0);
            kegelGame.roll(1);

            kegelGame.roll(7);
            kegelGame.roll(1);

            assertThat(kegelGame.calculateScore()).isEqualTo(-11);
            assertThatNoException().isThrownBy(() -> {
                kegelGame.calculateScore();
            });
        }

        @Test
        void calculateScore_perfectGame_maxPoints() {
            for (int frame = 1; frame < 9; frame++) {
                if (frame % 2 == 0) {
                    kegelGame.roll(0);
                    kegelGame.roll(1);
                } else {
                    kegelGame.roll(9);
                }

            }
            assertThat(kegelGame.calculateScore()).isEqualTo(32);
        }

        @Test
        void calculateScore_worstGame_maxPoints() {
            for (int frame = 1; frame < 9; frame++) {
                if (frame % 2 == 0) {
                    kegelGame.roll(9);
                } else {
                    kegelGame.roll(0);
                    kegelGame.roll(0);
                }

            }
            assertThat(kegelGame.calculateScore()).isEqualTo(-36);
        }

        @Test
        void illegalFrameScore() {
            kegelGame.roll(7);
            kegelGame.roll(7);
            assertThatThrownBy(() -> kegelGame.calculateScore()).isInstanceOf(RuntimeException.class).hasMessageContaining("More Pins knocked over than possible");
        }

        @Test
        void illegalRoll() {
            assertThatThrownBy(() -> kegelGame.roll(15)).isInstanceOf(RuntimeException.class).hasMessageContaining("To many pins rolled");
        }


    }

}