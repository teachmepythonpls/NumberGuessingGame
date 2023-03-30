import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Let's play a game. I'll pick a number between");
        System.out.println("1 and 100, and you try to guess it.");
        System.out.print("Enter your name: ");
        String playerName = sc.nextLine();
        Player player = new Player(playerName);

        boolean playAgain;
        do {
            System.out.println();
            System.out.println("Hi, " + player.getName() + ". What is your first guess?");
            playGame(player);

            System.out.println("Would you like to play again? (Y/N)");
            playAgain = sc.next().equalsIgnoreCase("Y");
        } while (playAgain);
        System.out.println("Exiting the game.");
    }
    static void playGame(Player player) {
        int computersNumber;
        int usersGuess;
        int guessCount;
        boolean guessedNumber = false;

        computersNumber = (int) (100 * Math.random()) + 1;
        guessCount = 0;

        Scanner sc = new Scanner(System.in);
        long gameStartTime = System.currentTimeMillis();
        long startTime = gameStartTime;
        while (guessCount < 5) {
            usersGuess = sc.nextInt();
            guessCount++;

            if (usersGuess == computersNumber) {
                long endTime = System.currentTimeMillis();
                long elapsedTime = endTime - gameStartTime;
                player.updateStats(elapsedTime, guessCount);
                System.out.println("You got it in " + guessCount
                        + " guesses! The number was " + computersNumber);
                System.out.println(player.getStats());
                guessedNumber = true;
                break;
            }
            if (guessCount == 3) {
                System.out.println("Here is the hint:");
                int firstDigit = Integer.parseInt(Integer.toString(computersNumber).substring(0, 1));
                System.out.println("The first digit of a number is " + firstDigit);
            }
            if (guessCount == 4) {
                System.out.println("You have one try left! Here is another hint:");
                if (computersNumber % 2 == 0) {
                    System.out.println("The number is even");
                } else {
                    System.out.println("The number is odd");
                }
            }
            if (usersGuess < computersNumber)
                System.out.println("The number is greater than "+usersGuess);
            else if (usersGuess > computersNumber)
                System.out.println("The number is lower than "+usersGuess);
        }
        if (!guessedNumber) {
            System.out.println("You didn't guess the number in 5 guesses.");
            System.out.println("You lose. My number was "+computersNumber);
        }

//        System.out.println(player.getStats());
    }
}
class Player {
    private String name;
    private long totalTime;
    private long currentGameTime;

    public Player(String name) {
        this.name = name;
        this.totalTime = 0;
        this.currentGameTime = 0;
    }
    public void updateStats(long elapsedTime, int guessCount) {
        this.totalTime += elapsedTime;
        this.currentGameTime = elapsedTime;
//        System.out.println("Time taken to finish the game: " + elapsedTime + "ms");
    }
    public String getName() {
        return name;
    }
    public String getStats() {
        return "Stats for " + name+":" + "\nTime taken for current game: " + currentGameTime + "ms\nTotal time taken: " + totalTime + "ms";
    }
}
