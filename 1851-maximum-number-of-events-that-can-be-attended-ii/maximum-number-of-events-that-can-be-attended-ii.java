class Solution {
    private int[][] events;
    private Integer[][] memo;

    public int maxValue(int[][] events, int k) {
        this.events = events;
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        int n = events.length;
        this.memo = new Integer[n][k + 1];
        return dfs(0, k);
    }

    // Returns maximum value starting from index i with up to rem events left
    private int dfs(int i, int rem) {
        if (rem == 0 || i == events.length) return 0;
        if (memo[i][rem] != null) return memo[i][rem];

        // Option 1: Skip this event
        int res = dfs(i + 1, rem);

        // Option 2: Attend this event
        int endTime = events[i][1];
        int idx = binarySearchNext(i + 1, endTime + 1);
        res = Math.max(res, events[i][2] + dfs(idx, rem - 1));

        return memo[i][rem] = res;
    }

    // Find smallest index with startDay >= target
    private int binarySearchNext(int lo, int target) {
        int hi = events.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (events[mid][0] >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}
