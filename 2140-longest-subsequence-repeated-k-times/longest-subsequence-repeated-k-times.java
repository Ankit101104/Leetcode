import java.util.*;

class Solution {
    public String longestSubsequenceRepeatedK(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        // Build the list of usable characters
        List<Character> available = new ArrayList<>();
        for (int c = 0; c < 26; c++) {
            if (freq[c] >= k) {
                available.add((char)('a' + c));
            }
        }

        String best = "";
        Queue<String> queue = new LinkedList<>();
        queue.offer("");

        while (!queue.isEmpty()) {
            String curr = queue.poll();
            // Prune impossible long candidates
            if (curr.length() * k > n) continue;

            for (char c : available) {
                String next = curr + c;
                if (isValidRepeatedSubsequence(s, next, k)) {
                    queue.offer(next);
                    if (next.length() > best.length() ||
                        (next.length() == best.length() && next.compareTo(best) > 0)) {
                        best = next;
                    }
                }
            }
        }

        return best;
    }

    // Check if string (t * k) is a subsequence of s
    private boolean isValidRepeatedSubsequence(String s, String t, int k) {
        int idx = 0, len = t.length();
        for (char ch : s.toCharArray()) {
            if (ch == t.charAt(idx % len)) {
                idx++;
                if (idx == len * k) return true;
            }
        }
        return false;
    }
}
