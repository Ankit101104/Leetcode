class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        int start = 0;

        for (int j = 0; j < n; j++) {
            if (nums[j] == key) {
                int left = Math.max(0, j - k);
                int right = Math.min(n - 1, j + k);
                while (start <= right) {
                    if (start >= left) {
                        ans.add(start);
                    }
                    start++;
                }
            }
        }

        return ans;
    }
}
