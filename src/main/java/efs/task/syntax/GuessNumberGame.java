package efs.task.syntax;

import java.util.Scanner;

public class GuessNumberGame {

    private final int numberToGuess;
    private final int maxAttempts;
    private final int upperBound;

    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {
        try {
            this.upperBound = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT.toUpperCase());
            throw new IllegalArgumentException("Nieprawidłowy argument: " + argument);
        }

        if (this.upperBound < 1 || this.upperBound > UsefulConstants.MAX_UPPER_BOUND) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT.toUpperCase());
            throw new IllegalArgumentException("Nieprawidłowy zakres: " + this.upperBound);
        }

        this.maxAttempts = (int) (Math.log(this.upperBound) / Math.log(2)) + 1; // na podstawie wzoru z readme
        this.numberToGuess = (int) (Math.random() * upperBound) + 1;
    }

    private void printProgressBar(int x, int y) {
        System.out.print("Postęp: [");

        for (int i = 0; i < x; i++) {
            System.out.print("*");
        }

        for (int i = 0; i < y; i++) {
            System.out.print(".");
        }

        System.out.println("]");
    }

    public void play() {
        System.out.println("Witaj w grze! Zgadnij liczbę z zakresu od <1," + this.upperBound + ">");

        Scanner scanner = new Scanner(System.in);

        int liczba_prob = 1;
        int number;

        while (liczba_prob <= maxAttempts) {
            printProgressBar(liczba_prob, maxAttempts - liczba_prob);
            System.out.println("PODAJ");
            String stringValue = scanner.nextLine();

            try {
                number = Integer.parseInt(stringValue);
            } catch (NumberFormatException e) {
                System.out.println("NIE LICZBA");
                liczba_prob++;
                continue;
            }

            if (number == numberToGuess) {
                System.out.println("TAK! Zgadłeś liczbę " + numberToGuess + " po " + liczba_prob + " próbach.");
                return;
            } else if (number > numberToGuess) {
                System.out.println("ZBYT WIELE");
            } else {
                System.out.println("NIE WYSTARCZY");
            }

            liczba_prob++;
        }

        System.out.println("NIESTETY");
    }
}
