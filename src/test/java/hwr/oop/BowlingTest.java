package hwr.oop;

public class BowlingTest {

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

}
