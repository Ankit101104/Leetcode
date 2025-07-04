class Solution {
    public char kthCharacter(long k, int[] operations) {
        int n = operations.length;
        // Determine how many operations affect the final string size ≥ k
        int opsCount = (int) Math.ceil(Math.log(k) / Math.log(2));

        long pos = k;
        int increments = 0;
        // Reverse simulation over relevant operations
        for (int i = opsCount - 1; i >= 0; i--) {
            long half = 1L << i;
            if (pos > half) {
                pos -= half;
                increments += operations[i];
            }
        }

        return (char) ('a' + (increments % 26));
    }
}
