class Solution {
    public int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int x=nums.length;
        int max=0;
        for(int i=0;i<x;i++){
            max=Math.max(nums[i]+nums[x-i-1],max);
        }
        return max;
        
    }
}