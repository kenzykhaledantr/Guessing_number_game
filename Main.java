import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        int randomNumber = r.nextInt(101);

//        System.out.println(randomNumber);
        System.out.println("Enter your user name : ");
        String name = sc.nextLine();
        int userAttempt = 0;
        Double Smaller=0.0;
        Double Larger = 0.0;
        Double mean;
        ArrayList<Double> values = new ArrayList<>();

        while (true) {
            try {
                FileWriter fw = new FileWriter("GuessReport.txt", true);
                System.out.println("Enter a number between 0 and 100:");
                int number = sc.nextInt();
                if (number < 0 || number > 100) {
                    userAttempt++;
                    throw new IllegalArgumentException("You entered an invalid number.");

                }
                if (number == randomNumber) {
                    userAttempt++;
                    System.out.println("Congratulations! You guessed the number.");
                    fw.write("\nUser name: " + name);
                    fw.write("\nNumber of attempts: " + userAttempt);
                    values.add((double)number);
                    mean=(Larger+Smaller+number)/values.size();
                    double squaredDiffSum = 0;
                    for (double num : values) {
                        double diff = num - mean;
                        squaredDiffSum += diff * diff;
                    }


                    double variance = squaredDiffSum / values.size();


                    double standardDeviation = Math.sqrt(variance);
                    fw.write("\nThe standard devision = " + standardDeviation);

                    fw.close();
                    break;
                } else if (number > randomNumber) {
                    System.out.println("Please guess a smaller number.");
                    Smaller=Smaller+(double)number;
                    values.add((double)number);


                } else if (number < randomNumber) {
                    System.out.println("Please guess a larger number.");
                    Larger=Larger+(double)number;
                    values.add((double)number);
                }
                userAttempt++;
            } catch (InputMismatchException e) {
                System.out.println("You entered an invalid input. Please enter a number between 0 and 100.");
                sc.nextLine();
                userAttempt++;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
