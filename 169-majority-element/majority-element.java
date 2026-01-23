class Solution {
    public int majorityElement(int[] nums) {
        int max = Integer.MIN_VALUE;
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}