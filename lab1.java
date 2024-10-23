import java.util.Scanner;

public class lab1 {
    public static void main(String[] args) {
        // Reading input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your credit card number: ");
        String ccNumber = scanner.nextLine();

        // Step 1: Check if the credit card number has between 8 and 9 digits
        if (ccNumber.length() < 8 || ccNumber.length() > 9) {
            System.out.println("Credit card not valid.");
            return; 
        }

        // Step a: Remove the last digit of the ccNumber
        int lastDigit = Character.getNumericValue(ccNumber.charAt(ccNumber.length() - 1));
        String remainingNumber = ccNumber.substring(0, ccNumber.length() - 1);
        System.out.println("Step a: Last digit = " + lastDigit + ", remaining number = " + remainingNumber);

        // Step b: Reverse the remaining digits
        String reversed = new StringBuilder(remainingNumber).reverse().toString();
        System.out.println("Step b: Reversed number = " + reversed);

        // Step c: Double the digits in odd-numbered positions
        int sum = 0; 
        for (int i = 0; i < reversed.length(); i++) {
            int digit = Character.getNumericValue(reversed.charAt(i));
            if (i % 2 == 0) { // for odd position
                int original = digit; 
                digit *= 2; // Doubling the digit
                if (digit > 9) {
                    digit = digit - 9; // If the result is double-digit, sum the digits
                }
                System.out.println("Step c: Digit at position " + (i + 1) + " was " + original + ", doubled to " + digit);
            }
            //step d: Add the current digit to the sum
            sum += digit;
        }
        System.out.println("Step d: Sum of all digits = " + sum);

        // Step e: Subtract the last digit obtained in step a from 10
        int checkDigit = (10 - (sum % 10)) % 10;
        System.out.println("Step e: 10 - (" + sum + " % 10) = " + checkDigit);

        // Step f: Compare the result of step e with the last digit obtained in step a
        if (checkDigit == lastDigit) {
            System.out.println("Step f: " + checkDigit + " matches " + lastDigit + ". Credit card number is valid.");
        } else {
            System.out.println("Step f: " + checkDigit + " does not match " + lastDigit + ". Credit card number is invalid.");
        }

        scanner.close();
    }
}

