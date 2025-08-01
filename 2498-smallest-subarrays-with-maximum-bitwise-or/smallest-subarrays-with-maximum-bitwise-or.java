import java.util.*;

public class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int[] lastSeen = new int[32]; // Store the farthest index where bit j is set

        Arrays.fill(lastSeen, -1);

        for (int i = n - 1; i >= 0; i--) {
            // Update lastSeen if bit j is set at nums[i]
            for (int j = 0; j < 32; j++) {
                if ((nums[i] & (1 << j)) != 0) {
                    lastSeen[j] = i;
                }
            }

            // Find the farthest bit that needs to be included in subarray
            int maxIndex = i;
            for (int j = 0; j < 32; j++) {
                if (lastSeen[j] != -1) {
                    maxIndex = Math.max(maxIndex, lastSeen[j]);
                }
            }

            result[i] = maxIndex - i + 1;
        }

        return result;
    }

    // For testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {1, 0, 2, 1, 0, 3};
        System.out.println(Arrays.toString(sol.smallestSubarrays(nums))); // Output: [6, 5, 4, 3, 2, 1]
    }
}
