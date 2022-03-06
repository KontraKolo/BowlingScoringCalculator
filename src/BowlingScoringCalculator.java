public interface BowlingScoringCalculator {

    void roll(int numberOfPinsHit);

    int score() throws Exception;
    
}
