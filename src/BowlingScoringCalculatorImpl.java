
public class BowlingScoringCalculatorImpl implements BowlingScoringCalculator{

    // number of rolls
    private int roll = 0;

    // Array with length of maximal rolls
    private int [] rolls = new int [21];

    // number of Frames (Bowling Score Frames)
    private final int numberOfFrames = 10;


    @Override
    public void roll(int numberOfPinsHit){
        if (numberOfPinsHit > 10 || numberOfPinsHit < 0){
            throw new IllegalArgumentException("The number of hit pins must be 0 to 10");
        }
        rolls[roll++] = numberOfPinsHit;
    }

    @Override
    public int score() throws Exception {
        int score = 0;
        int toss = 0;
        for (int frame = 0; frame < numberOfFrames; frame++)
            if (isStrike(toss)){
                score += 10 + rolls[toss + 1] + rolls[toss + 2];
                toss++;
            }
            else if (isSpare(toss)){
                score += 10 + rolls[toss + 2];
                toss += 2;
            }
            else if(isRuleViolation(toss)){
                throw new Exception("Two rolls together should not be over 10");
            }
            else {
                score += rolls[toss] + rolls[toss +1];
                toss += 2;
            }
            return score;
    }


    public boolean isStrike(int toss){
        return rolls[toss] == 10;
    }


    public boolean isSpare(int toss){
        return rolls[toss] + rolls[toss+1] == 10;
    }

    // The result of a pair of rolls should not be over 10 (only ten pins per round)

    public boolean isRuleViolation(int toss){
        return rolls[toss] + rolls[toss+1] > 10;
    }
}
