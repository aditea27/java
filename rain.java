interface WaterConservationSystem {
    int calculateTrappedWater(int[] blockHeights);
}
abstract class RainySeasonConservation implements WaterConservationSystem {
    //(currently empty)
}
class CityBlockConservation extends RainySeasonConservation {

    @Override
    public int calculateTrappedWater(int[] blockHeights) {
        if (blockHeights == null || blockHeights.length < 3) {
            return 0;
        }

        int n = blockHeights.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        int trappedWater = 0;

        // Step 1: Compute the maximum height to the left of each block
        leftMax[0] = blockHeights[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], blockHeights[i]);
        }

        // Step 2: Compute the maximum height to the right of each block
        rightMax[n - 1] = blockHeights[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], blockHeights[i]);
        }

        // Step 3: Calculate the trapped water at each block
        for (int i = 0; i < n; i++) {
            // Water trapped at current block is the minimum of max heights on both sides minus the block height
            trappedWater += Math.min(leftMax[i], rightMax[i]) - blockHeights[i];
        }

        return trappedWater; // Return the total volume of trapped water
    }
}


public class rain {
    public static void main(String[] args) {
      
        int[] blockHeights = {1, 2, 3, 4};        
        CityBlockConservation conservationSystem = new CityBlockConservation();        
        int totalTrappedWater = conservationSystem.calculateTrappedWater(blockHeights);
        System.out.println("Total Trapped Water: " + totalTrappedWater);
    }
}
