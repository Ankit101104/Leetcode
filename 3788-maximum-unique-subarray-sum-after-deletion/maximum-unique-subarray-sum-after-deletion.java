class Solution {
    public int maxSum(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int v : nums) max = Math.max(max, v);

        if (max <= 0) {
            return max;
        }

        boolean[] seen = new boolean[201]; // since nums[i] in [-100,100]
        int sum = 0;
        for (int v : nums) {
            if (v >= 0 && !seen[v]) {
                sum += v;
                seen[v] = true;
            }
        }

        return sum;
    }
}
