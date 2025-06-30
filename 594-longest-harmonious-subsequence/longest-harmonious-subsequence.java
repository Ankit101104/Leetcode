class Solution {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int ans = 0;

        // Build frequency map
        for (int x : nums) {
            freq.put(x, freq.getOrDefault(x, 0) + 1);
        }

        // Check consecutive pairs
        for (var entry : freq.entrySet()) {
            int x = entry.getKey();
            int countX = entry.getValue();
            if (freq.containsKey(x + 1)) {
                int current = countX + freq.get(x + 1);
                ans = Math.max(ans, current);
            }
        }

        return ans;
    }
}
