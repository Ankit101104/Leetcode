import java.util.*;

class Solution {
    public int minimumDeletions(String word, int k) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        int ans = Integer.MAX_VALUE;
        for (int v = 0; v <= word.length(); v++) {
            int deletions = 0;
            for (int f : freq) {
                if (f < v) deletions += f;
                else if (f > v + k) deletions += f - (v + k);
            }
            ans = Math.min(ans, deletions);
        }
        return ans;
    }
}
