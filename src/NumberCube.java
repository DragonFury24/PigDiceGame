import java.util.Random;

public class NumberCube {
    public NumberCube() {
    }

    private Random generator = new Random(); //Object that creates random numbers
    private int rollValue = 0; //Current value of side of the sube that is face up
    private int previousRoll = 0; //Holds the previous value so double roll checks can be used

    public int roll() { //Simulates dice roll and returns value of side that is face up
        previousRoll = rollValue;
        rollValue = generator.nextInt(6) + 1;
        return rollValue;
    }

    public int getRoll() { //Returns value of side that is faceup
        return rollValue;
    }

    public int getPreviousRoll() { //Returns value of roll before the current @rollValue
        return previousRoll;
    }

    public String toString() {
        return "Die Value: " + rollValue;
    }
}
