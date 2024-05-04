import java.util.Scanner;

public class Factorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number
        System.out.print("Enter a number to find its factorial: ");
        int number = scanner.nextInt();

        // Calculate factorial
        long factorial = calculateFactorial(number);

        // Print the result
        System.out.println("The factorial of " + number + " is: " + factorial);

        scanner.close();
    }

    // Function to calculate factorial
    public static long calculateFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            long result = 1;
            for (int i = 2; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }
}