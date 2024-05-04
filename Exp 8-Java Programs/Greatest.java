import java.util.Scanner;

public class Greatest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input first number
        System.out.print("Enter the first number: ");
        double num1 = scanner.nextDouble();

        // Input second number
        System.out.print("Enter the second number: ");
        double num2 = scanner.nextDouble();

        // Find the greatest number
        double greatest = Math.max(num1, num2);

        // Print the result
        System.out.println("The greatest number is: " + greatest);

        scanner.close();
    }
}
