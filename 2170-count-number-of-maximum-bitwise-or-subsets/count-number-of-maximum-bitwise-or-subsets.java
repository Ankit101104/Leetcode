class Solution {
    private int target;
    private int count = 0;
    private int[] nums;

    public int countMaxOrSubsets(int[] nums) {
        this.nums = nums;
        target = 0;
        for (int x : nums) target |= x;

        dfs(0, 0);
        return count;
    }

    private void dfs(int index, int currentOr) {
        if (index == nums.length) {
            if (currentOr == target) count++;
            return;
        }
        // Exclude this element
        dfs(index + 1, currentOr);
        // Include this element
        dfs(index + 1, currentOr | nums[index]);
    }
}
