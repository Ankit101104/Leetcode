class Solution {
    public int findLucky(int[] arr) {
        int n = arr.length;
        int[] freq = new int[n + 1];
        for (int x : arr) {
            if (x <= n) freq[x]++;
        }

        for (int i = n; i >= 1; i--) {
            if (freq[i] == i) return i;
        }

        return -1;
    }
}
