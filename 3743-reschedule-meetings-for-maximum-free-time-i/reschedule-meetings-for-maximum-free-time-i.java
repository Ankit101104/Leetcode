class Solution {
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] gaps = new int[n + 1];
        
        gaps[0] = startTime[0];
        for (int i = 1; i <= n - 1; i++) {
            gaps[i] = startTime[i] - endTime[i - 1];
        }
        gaps[n] = eventTime - endTime[n - 1];

        int windowSum = 0, ans = 0;
        for (int i = 0; i <= k; i++) windowSum += gaps[i];
        ans = windowSum;

        for (int i = k + 1; i < gaps.length; i++) {
            windowSum += gaps[i] - gaps[i - (k + 1)];
            ans = Math.max(ans, windowSum);
        }

        return ans;
    }
}
