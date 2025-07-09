class Solution {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int day = 0, i = 0, count = 0;
        int n = events.length;

        while (i < n || !pq.isEmpty()) {
            // Jump day forward if no events available
            if (pq.isEmpty()) {
                day = Math.max(day, events[i][0]);
            }

            // Add all events starting today
            while (i < n && events[i][0] == day) {
                pq.offer(events[i][1]);
                i++;
            }

            // Remove expired events
            while (!pq.isEmpty() && pq.peek() < day) {
                pq.poll();
            }

            // Attend the event that ends earliest
            if (!pq.isEmpty()) {
                pq.poll();
                count++;
                day++;
            }
        }

        return count;
    }
}
