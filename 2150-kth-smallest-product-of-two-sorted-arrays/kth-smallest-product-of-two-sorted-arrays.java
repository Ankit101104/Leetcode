class Solution {
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        // Split negative and non-negative parts for both arrays
        List<Integer> A1 = new ArrayList<>(), A2 = new ArrayList<>();
        List<Integer> B1 = new ArrayList<>(), B2 = new ArrayList<>();
        split(nums1, A1, A2);
        split(nums2, B1, B2);

        // Count total negative products
        long negCount = (long) A1.size() * B2.size() + (long) A2.size() * B1.size();
        boolean isNegative = false;
        if (k <= negCount) {
            isNegative = true;
            k = negCount - k + 1;
            // Swap B1 and B2 to focus on absolute negative products
            List<Integer> tmp = B1; B1 = B2; B2 = tmp;
        } else {
            k -= negCount;
        }

        long left = 0, right = (long)1e10;
        while (left < right) {
            long mid = (left + right) >>> 1;
            long cnt = countPairs(A1, B1, mid) + countPairs(A2, B2, mid);
            if (cnt >= k) right = mid;
            else left = mid + 1;
        }

        return isNegative ? -left : left;
    }

    private void split(int[] arr, List<Integer> neg, List<Integer> pos) {
        for (int x : arr) {
            if (x < 0) neg.add(-x);
            else pos.add(x);
        }
        Collections.sort(neg); // Ensure ascending order
    }

    // Count (a * b) <= target for sorted lists A and B
    private long countPairs(List<Integer> A, List<Integer> B, long t) {
        long count = 0;
        int j = B.size() - 1;
        for (long a : A) {
            while (j >= 0 && a * B.get(j) > t) j--;
            count += j + 1;
        }
        return count;
    }
}
