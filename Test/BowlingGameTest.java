import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BowlingGameTest {

    private BowlingScoringCalculatorImpl bowlingScoringCalculator;
    private int[] rolls;

    @BeforeEach
    public void setUp() {
        bowlingScoringCalculator = new BowlingScoringCalculatorImpl();
    }

    // Helper method
    private void roll(int[] rolls) {
        for (int numberOfPinsHit : rolls) {
            bowlingScoringCalculator.roll(numberOfPinsHit);
        }
    }

    @Test
    public void scoreAboveTen() {
        int[] rollWithElevenPoints = new int[]{22};
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> roll(rollWithElevenPoints));
        Assertions.assertEquals(exception.getMessage(), "The number of hit pins must be 0 to 10");
    }

    @Test
    public void negativeScore() {
        int[] negativeScore = new int[]{-3};
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> roll(negativeScore));
        Assertions.assertEquals(exception.getMessage(), "The number of hit pins must be 0 to 10");
    }

    // Expected is 18, because 10+2+2 = 14 and since 10 is a strike
    // we add the next 2 rolls as a bonus -> 10+2+2+2+2 = 18
    @Test
    public void checkStrike() throws Exception {
        rolls = new int[]{10, 2, 2};
        roll(rolls);
        Assertions.assertEquals(18, bowlingScoringCalculator.score());
    }

    // Expected is 14, because 5+5+2 = 12 and since the first two rolls are together 10
    // we add the next roll as a bonus -> 5+5+2+2
    @Test
    public void checkSpare() throws Exception {
        rolls = new int[]{5, 5, 2};
        roll(rolls);
        Assertions.assertEquals(14, bowlingScoringCalculator.score());
    }

    // Test score without a strike, spear or an exception
    @Test
    public void gameWithOneScoreInAllRolls() throws Exception {
        rolls = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
        roll(rolls);
        Assertions.assertEquals(20, bowlingScoringCalculator.score());
    }

    @Test
    public void SumOfFrameOver10() throws Exception {
        rolls = new int[]{6, 6};
        roll(rolls);
        Throwable exception = assertThrows(Exception.class, () -> bowlingScoringCalculator.score());
        Assertions.assertEquals(exception.getMessage(), "Two rolls together should not be over 10");
    }

    // This tests the calculation included Strikes and Spears
    @Test
    public void FullGame() throws Exception {
        rolls = new int[]{1, 4, 4, 5, 6, 4, 5, 5, 10, 0, 1, 7, 3, 6, 4, 10, 2, 8, 6};
        roll(rolls);
        Assertions.assertEquals(133, bowlingScoringCalculator.score());
    }
}
