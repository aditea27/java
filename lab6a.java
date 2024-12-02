import java.util.*;
import java.util.concurrent.*;

class CoinCombinationTask implements Callable<List<List<Integer>>> {
    private int[] coins;
    private int sum;
    private int index;

    public CoinCombinationTask(int[] coins, int sum, int index) {
        this.coins = coins;
        this.sum = sum;
        this.index = index;
    }

    private void findCombinations(int[] coins, int index, int sum, List<Integer> current, List<List<Integer>> result) {
        if (sum == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (sum < 0 || index >= coins.length) {
            return;
        }

        // Include the current coin
        current.add(coins[index]);
        findCombinations(coins, index, sum - coins[index], current, result);
        current.remove(current.size() - 1); // Backtrack

        // Exclude the current coin
        findCombinations(coins, index + 1, sum, current, result);
    }

    @Override
    public List<List<Integer>> call() {
        List<List<Integer>> combinations = new ArrayList<>();
        findCombinations(coins, index, sum, new ArrayList<>(), combinations);
        return combinations;
    }
}

public class lab6a {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter the number of coins: ");
        int n = sc.nextInt();

        System.out.print("Enter the target sum: ");
        int sum = sc.nextInt();

        System.out.print("Enter the coin denominations: ");
        int[] coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = sc.nextInt();
        }

        // Multithreading
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<List<List<Integer>>>> futures = new ArrayList<>();

        // Divide work for each thread
        for (int i = 0; i < n; i++) {
            futures.add(executor.submit(new CoinCombinationTask(coins, sum, i)));
        }

        // Collect results
        List<List<Integer>> allCombinations = new ArrayList<>();
        for (Future<List<List<Integer>>> future : futures) {
            allCombinations.addAll(future.get());
        }
        executor.shutdown();

        // Remove duplicates
        Set<List<Integer>> uniqueCombinations = new HashSet<>();
        for (List<Integer> combination : allCombinations) {
            Collections.sort(combination); // Normalize order
            uniqueCombinations.add(combination);
        }

        // Output
        System.out.println("Input:");
        System.out.println("N = " + n + ", sum = " + sum);
        System.out.println("coins = " + Arrays.toString(coins));

        System.out.println("Output: " + uniqueCombinations.size());
        System.out.println("Explanation: Possible ways are:");
        for (List<Integer> combination : uniqueCombinations) {
            System.out.println(combination);
        }
    }
}




