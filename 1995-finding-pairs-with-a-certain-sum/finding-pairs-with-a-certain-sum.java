class FindSumPairs {
    private int[] nums1, nums2;
    private Map<Integer, Integer> freq2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.freq2 = new HashMap<>();
        for (int v : nums2) {
            freq2.merge(v, 1, Integer::sum);
        }
    }

    public void add(int index, int val) {
        int old = nums2[index];
        // Decrement old value's frequency
        freq2.merge(old, -1, Integer::sum);
        nums2[index] += val;
        // Increment new value's frequency
        freq2.merge(nums2[index], 1, Integer::sum);
    }

    public int count(int tot) {
        int ans = 0;
        // For each num in nums1, add the count of (tot - num) in nums2
        for (int x : nums1) {
            ans += freq2.getOrDefault(tot - x, 0);
        }
        return ans;
    }
}
