import java.util.*;

public class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> result = new HashSet<>();
        Set<Integer> prev = new HashSet<>();

        for (int num : arr) {
            Set<Integer> curr = new HashSet<>();
            curr.add(num);

            for (int val : prev) {
                curr.add(num | val);
            }

            result.addAll(curr);
            prev = curr;
        }

        return result.size();
    }

    // For testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1, 2, 4};
        System.out.println(sol.subarrayBitwiseORs(arr));  // Output: 6
    }
}
