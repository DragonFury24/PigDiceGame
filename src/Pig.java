import java.util.Scanner;

public class Pig {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        NumberCube numberCube = new NumberCube();
        int player1Score = 0, player2Score = 0;

        //Values that identify the player's turn
        final short player1Turn = 0;
        final short player2Turn = 1;

        int turn = 0; //Keeps track of current turn

        System.out.println("Player 1 turn.");
        System.out.println("Type R to roll the dice.");
        if (keyboard.nextLine().equalsIgnoreCase("R"))
            player1Score += numberCube.roll();
        System.out.println("You rolled " + numberCube.getRoll() + "!");
        System.out.println("Your current score is " + player1Score + ".");
        turn = numberCube.getRoll() == 1 ? player2Turn : turn;

        while (player1Score < 100 && player2Score < 100) {
            {
            while (turn == player1Turn) {
                System.out.println("Type R to roll the dice or E to end your turn.");
                if (keyboard.nextLine().equalsIgnoreCase("R"))
                    player1Score += numberCube.roll();
                else if (keyboard.nextLine().equalsIgnoreCase("E"))
                    turn = player2Turn;
                System.out.println("You rolled " + numberCube.getRoll() + "!");
                System.out.println("Your current score is " + player1Score + ".");
                if (player1Score >= 100)
                    break;
                turn = numberCube.getRoll() == 1 ? player2Turn : turn;
            }
            if (player1Score >= 100)
                break;
        }

            while (turn == player2Turn) {
                System.out.println("Type R to roll the dice or E to end your turn.");
                if (keyboard.nextLine().equalsIgnoreCase("R")) {
                    if (numberCube.roll() == numberCube.getPreviousRoll())
                        player2Score += numberCube.roll();
                }
                System.out.println("You rolled " + numberCube.getRoll() + "!");
                System.out.println("Your current score is " + player2Score + ".");
                if (player2Score >= 100)
                    break;
                turn = numberCube.getRoll() == 1 ? player1Turn : turn;
            }
        }
        if (player1Score > player2Score)
            System.out.println("Player 1 wins!");
        else System.out.print("Player 2 wins!");

    }
}
