class Solution {
    public int countHillValley(int[] nums) {
        int count = 0;
        int prev = 0;  // index of last non-equal value

        for (int i = 1; i + 1 < nums.length; i++) {
            if (nums[i] == nums[i + 1]) {
                continue;  // skip duplicates
            }
            if ((nums[prev] < nums[i] && nums[i] > nums[i + 1]) ||
                (nums[prev] > nums[i] && nums[i] < nums[i + 1])) {
                count++;
            }
            prev = i;
        }

        return count;
    }
}
