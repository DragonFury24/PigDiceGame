import java.util.Random;

public class NumberCube {
    public NumberCube() {
        rollValue = roll(); //Sets dice to a value, but will not return face up side
    }

    Random generator = new Random(); //Object that creates random numbers
    private int rollValue = 0; //Current value of side of the sube that is face up

    public int roll() { //Simulates dice roll and returns value of side that is face up
        rollValue = generator.nextInt(6) + 1;
        return rollValue;
    }

    public int getRoll() { //Returns value of side that is faceup
        return rollValue;
    }
}
