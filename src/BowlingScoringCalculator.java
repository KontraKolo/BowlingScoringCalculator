public interface BowlingScoringCalculator {

    void roll(int numberOfPinsHit);

    int score() throws Exception;

    boolean isStrike(int toss);

    boolean isSpare(int toss);

    boolean isRuleViolation(int toss);
}
