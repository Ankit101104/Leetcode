class Solution {
    public char kthCharacter(int k) {
        // Count the number of 1s in k - 1, then offset from 'a'
        return (char) ('a' + Integer.bitCount(k - 1));
    }
}
