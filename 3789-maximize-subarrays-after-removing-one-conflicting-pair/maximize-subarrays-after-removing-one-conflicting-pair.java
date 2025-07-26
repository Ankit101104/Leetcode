class Solution {
  public long maxSubarrays(int n, int[][] conflictingPairs) {
    // Normalize and sort by ending point
    for (int[] p : conflictingPairs) {
      if (p[0] > p[1]) {
        int tmp = p[0]; p[0] = p[1]; p[1] = tmp;
      }
    }
    Arrays.sort(conflictingPairs, Comparator.comparingInt(p -> p[1]));

    long blocked = 0, profit = 0, maxProfit = 0;
    long max1 = 0, max2 = 0;

    int m = conflictingPairs.length;
    for (int i = 0; i < m; ++i) {
      long start = conflictingPairs[i][0];
      long end = conflictingPairs[i][1];
      long bottom = (i + 1 < m ? conflictingPairs[i + 1][1] : n + 1);
      long gap = bottom - end;

      if (start > max1) {
        max2 = max1;
        max1 = start;
        profit = 0;
      } else if (start > max2) {
        max2 = start;
      }

      profit += (max1 - max2) * gap;
      maxProfit = Math.max(maxProfit, profit);
      blocked += max1 * gap;
    }

    long total = n * 1L * (n + 1) / 2;
    return total - blocked + maxProfit;
  }
}
