class Solution {
    public long kMirror(int k, int n) {
        long sum = 0;
        int count = 0;
        int len = 1;

        while (count < n) {
            // Generate palindromes of base 10 with current length
            for (long i = (long)Math.pow(10, (len - 1) / 2); count < n && i < (long)Math.pow(10, (len + 1) / 2); i++) {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                // Make even/odd-length palindromes
                for (int j = (len % 2 == 0) ? sb.length() - 1 : sb.length() - 2; j >= 0; j--) {
                    sb.append(sb.charAt(j));
                }
                long val = Long.parseLong(sb.toString());

                if (isKMirror(val, k)) {
                    sum += val;
                    count++;
                }
            }
            len++;
        }

        return sum;
    }

    // Check if a number is a palindrome in base k
    private boolean isKMirror(long val, int k) {
        StringBuilder sb = new StringBuilder();
        while (val > 0) {
            sb.append(val % k);
            val /= k;
        }
        String baseK = sb.toString();
        int left = 0, right = baseK.length() - 1;
        while (left < right) {
            if (baseK.charAt(left++) != baseK.charAt(right--)) return false;
        }
        return true;
    }
}
