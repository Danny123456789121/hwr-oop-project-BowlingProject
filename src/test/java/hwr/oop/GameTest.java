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
        void calculateScore_PlayValidGame_ScoreCalculatedCorrectlyWithAllRulesApplied() {
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
        void calculateScore_RollNoSpareOrStrikeInLastFrame_ThirdRollWillNotBeCounted() {
            rollMany(18, 0, bowlingGame);
            bowlingGame.roll(4);
            bowlingGame.roll(5);

            bowlingGame.roll(4);

            assertThat(bowlingGame.calculateScore()).isEqualTo(9);
        }

        @Test
        void sumOfPinsInFrame_RollMorePinsThanPossibleInAFrame_RuntimeExceptionThrown() {
            bowlingGame.roll(9);
            bowlingGame.roll(9);
            assertThatThrownBy(() -> bowlingGame.sumOfPinsInFrame(0)).isInstanceOf(RuntimeException.class).hasMessageContaining("More Pins knocked over than possible");
        }

        @Test
        void roll_RollToManyPinsPossible_RuntimeExceptionThrown() {
            assertThatThrownBy(() -> bowlingGame.roll(15)).isInstanceOf(RuntimeException.class).hasMessageContaining("To many pins rolled");
        }

        @Test
        void calculateScore_RollWorstGame_FinalScoreIsZero() {
            rollMany(20, 0, bowlingGame);
            assertThat(bowlingGame.calculateScore()).isZero();
        }

        @Test
        void calculateScore_RollPerfectGame_MaxPoints() {
            rollMany(12, 10, bowlingGame);
            assertThat(bowlingGame.calculateScore()).isEqualTo(300);
        }

        @Test
        void calculateScore_RollStrike_StrikeAndStrikeBonusReflectedInScore() {
            rollStrike();
            bowlingGame.roll(4);
            bowlingGame.roll(3);
            rollMany(16, 0, bowlingGame);
            assertThat(bowlingGame.calculateScore()).isEqualTo(24);
        }

        @Test
        void calculateScore_RollSpare_SpareAndSpareBonusReflectedInScore() {
            rollSpare();
            bowlingGame.roll(4);
            rollMany(17, 0, bowlingGame);
            assertThat(bowlingGame.calculateScore()).isEqualTo(18);
        }

        @Test
        void calculateScore_RollSpareFollowedByStrike_SpareAndStrikeAreReflectedInScore() {
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
        void calculateScore_PlayValidGame_ScoreCalculatedCorrectlyWithAllRulesApplied() {
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
        void calculateScore_RollPerfectGame_MaxPoints() {
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
        void calculateScore_RollWorstGame_WorstScore() {
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
        void sumOfPinsInFrame_RollMorePinsThanPossibleInAFrame_RuntimeExceptionThrown() {
            kegelGame.roll(7);
            kegelGame.roll(7);
            assertThatThrownBy(() -> kegelGame.sumOfPinsInFrame(0)).isInstanceOf(RuntimeException.class).hasMessageContaining("More Pins knocked over than possible");
        }

        @Test
        void roll_RollToManyPinsPossible_RuntimeExceptionThrown() {
            assertThatThrownBy(() -> kegelGame.roll(15)).isInstanceOf(RuntimeException.class).hasMessageContaining("To many pins rolled");
        }


    }

}