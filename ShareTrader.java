import java.util.Scanner;

public class ShareTrader {
    static int maxProfit = 0;

    public static int findMaxProfit(int[] prices) {
        int n = prices.length;
        if (n < 2) {
            return 0; 
        }

        int[] leftProfit = new int[n];
        int[] rightProfit = new int[n];
        int minPrice = prices[0];  
        int firstBuy = 0, firstSell = 0;
        int secondBuy = 0, secondSell = 0;

        // Calculate the maximum profit possible up to each day (left to right)
        for (int i = 1; i < n; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            leftProfit[i] = Math.max(leftProfit[i - 1], prices[i] - prices[firstBuy]);  // Buy at the first price
        }

        // Calculate the maximum profit possible after each day (right to left)
        int maxPrice = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            maxPrice = Math.max(maxPrice, prices[i]);
            rightProfit[i] = Math.max(rightProfit[i + 1], maxPrice - prices[i]);
        }

        // Calculate the total maximum profit considering two transactions
        for (int i = 0; i < n; i++) {
            if (leftProfit[i] + rightProfit[i] > maxProfit) {
                maxProfit = leftProfit[i] + rightProfit[i];
                firstSell = i;
            }
        }

        // Finding for second transaction
        for (int i = firstSell + 1; i < n; i++) {
            if (rightProfit[i] == maxPrice - prices[i]) {
                secondBuy = i;
                secondSell = n - 1;
                break;
            }
        }

        System.out.println("Buy at price " + prices[firstBuy] + ", sell at " + prices[firstSell] + 
                           ", buy at " + prices[secondBuy] + " and sell at " + prices[secondSell]);

        return maxProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Taking input
        System.out.println("Enter the number of prices:");
        int n = sc.nextInt();
        int[] prices = new int[n];

        System.out.println("Enter the prices:");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        // Calculate and print the maximum profit and transactions
        int profit = findMaxProfit(prices);
        System.out.println("Total earnings " + profit);
    }
}
