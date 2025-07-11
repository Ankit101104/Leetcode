class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // Sort meetings by original start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));

        // Min-heap of available room IDs
        PriorityQueue<Integer> available = new PriorityQueue<>();
        for (int i = 0; i < n; i++) available.offer(i);

        // Min-heap of busy rooms: [availableTime, roomID]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1])
        );

        int[] count = new int[n];

        for (int[] m : meetings) {
            int start = m[0], end = m[1];
            // Free rooms whose meetings have ended
            while (!busy.isEmpty() && busy.peek()[0] <= start) {
                available.offer((int) busy.poll()[1]);
            }

            if (!available.isEmpty()) {
                // Assign available room
                int room = available.poll();
                count[room]++;
                busy.offer(new long[]{end, room});
            } else {
                // Delay meeting to earliest end time
                long[] next = busy.poll();
                int room = (int) next[1];
                long newEnd = next[0] + (end - start);
                count[room]++;
                busy.offer(new long[]{newEnd, room});
            }
        }

        // Find room with maximum meetings
        int ans = 0;
        for (int i = 1; i < n; i++) {
            if (count[i] > count[ans]) ans = i;
        }
        return ans;
    }
}
