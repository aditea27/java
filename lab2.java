import java.util.*;

public class lab2 {

    public static void main(String[] args) {
        // Input for the array
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array:");
        int size = scanner.nextInt();

        if (size <= 0) {
            System.out.println("Size must be a positive integer.");
            return;
        }

        int[] numbers = new int[size];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < size; i++) {
            numbers[i] = scanner.nextInt();
        }

        System.out.println("Enter the value of K:");
        int K = scanner.nextInt();

        // Calling the method to find and display the top K frequent numbers
        findTopKFrequent(numbers, K);
    }

    // Static method to find and display the top K frequent numbers
    public static void findTopKFrequent(int[] numbers, int K) {
        // Find the maximum value in the array to create the frequency array
        int maxVal = Integer.MIN_VALUE;
        for (int num : numbers) {
            if (num > maxVal) {
                maxVal = num;
            }
        }

        // Frequency array to count occurrences of numbers
        int[] frequency = new int[maxVal + 1];

        // Counting the frequency of each number in the array
        for (int num : numbers) {
            frequency[num]++;
        }

        // Creating a list to store numbers based on their frequency
        List<Integer> topKList = new ArrayList<>();
        
        // Finding top K frequent numbers
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] > 0) {
                topKList.add(i); // Adding the number to the list if it appears in the frequency array
            }
        }

        // Sorting the list based on frequency and number value
        topKList.sort((a, b) -> {
            if (frequency[b] != frequency[a]) {
                return frequency[b] - frequency[a]; // Sorting by frequency (descending)
            }
            return b - a; // If frequency same, sort by number (descending)
        });

        // Display the top K frequent numbers
        System.out.print("Output: ");
        for (int i = 0; i < K && i < topKList.size(); i++) {
            System.out.print(topKList.get(i) + " ");
        }
        System.out.println(); 
    }
}

