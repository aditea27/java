import java.util.Scanner;

abstract class Robber {
    // Abstract methods
    public abstract int RowHouses(int[] amounts);
    public abstract int RoundHouses(int[] amounts);
    public abstract int SquareHouse(int[] amounts);
    public abstract int MultiHouseBuilding(int[]... buildings);

    // Default method
    public void MachineLearning() {
        System.out.println("I love MachineLearning.");
    }

    // Method to print MScAI&ML
    public void RobbingClass() {
        System.out.println("MScAI&ML");
    }
}

class JAVAProfessionalRobber extends Robber {
    // Utility function to find max amount without adjacent robberies
    private int robLinear(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int prev1 = 0, prev2 = 0;
        for (int num : nums) {
            int temp = prev1;
            prev1 = Math.max(prev1, prev2 + num);
            prev2 = temp;
        }
        return prev1;
    }

    @Override
    public int RowHouses(int[] amounts) {
        return robLinear(amounts);
    }

    @Override
    public int RoundHouses(int[] amounts) {
        if (amounts.length == 1) return amounts[0];
        int[] excludeFirst = new int[amounts.length - 1];
        int[] excludeLast = new int[amounts.length - 1];
        System.arraycopy(amounts, 1, excludeFirst, 0, amounts.length - 1);
        System.arraycopy(amounts, 0, excludeLast, 0, amounts.length - 1);
        return Math.max(robLinear(excludeFirst), robLinear(excludeLast));
    }

    @Override
    public int SquareHouse(int[] amounts) {
        return robLinear(amounts);
    }

    @Override
    public int MultiHouseBuilding(int[]... buildings) {
        int totalMax = 0;
        for (int[] building : buildings) {
            totalMax += robLinear(building);
        }
        return totalMax;
    }
}

public class lab4 {
    // Utility function to parse input arrays
    private static int[] parseArray(String input) {
        input = input.replaceAll("[\\[\\]]", ""); // Remove brackets
        String[] parts = input.split(",");
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i].trim());
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JAVAProfessionalRobber robber = new JAVAProfessionalRobber();

        // Display default messages
        robber.RobbingClass();
        robber.MachineLearning();

        System.out.println("Enter test case for RowHouses (e.g., [1,2,3,0]): ");
        int[] rowHouses = parseArray(scanner.nextLine());
        System.out.println("RowHouses Maximum: " + robber.RowHouses(rowHouses));

        System.out.println("Enter test case for RoundHouses (e.g., [1,2,3,4]): ");
        int[] roundHouses = parseArray(scanner.nextLine());
        System.out.println("RoundHouses Maximum: " + robber.RoundHouses(roundHouses));

        System.out.println("Enter test case for SquareHouse (e.g., [5,10,2,7]): ");
        int[] squareHouses = parseArray(scanner.nextLine());
        System.out.println("SquareHouse Maximum: " + robber.SquareHouse(squareHouses));

        System.out.println("Enter the number of house types for MultiHouseBuilding: ");
        int numTypes = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        int[][] multiHouseBuildings = new int[numTypes][];
        for (int i = 0; i < numTypes; i++) {
            System.out.println("Enter amounts for house type " + (i + 1) + " (e.g., [5,3,8,2]): ");
            multiHouseBuildings[i] = parseArray(scanner.nextLine());
        }
        System.out.println("MultiHouseBuilding Maximum: " + robber.MultiHouseBuilding(multiHouseBuildings));

        scanner.close();
    }
}


