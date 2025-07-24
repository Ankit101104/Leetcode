class Solution {
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        int totalXor = 0;
        for (int v : nums) totalXor ^= v;

        // Build graph
        List<Integer>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (var e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        // Compute subtree XORs and descendants via DFS
        int[] subXor = nums.clone();
        Set<Integer>[] children = new Set[n];
        for (int i = 0; i < n; i++) {
            children[i] = new HashSet<>();
            children[i].add(i);
        }
        dfs(0, -1, graph, subXor, children);

        int ans = Integer.MAX_VALUE;
        // Iterate through each pair of edges
        for (int i = 0; i < edges.length; i++) {
            int a = edges[i][0], b = edges[i][1];
            // identify child and parent in edge1
            if (children[a].contains(b)) { int tmp = a; a = b; b = tmp; }

            for (int j = i+1; j < edges.length; j++) {
                int c = edges[j][0], d = edges[j][1];
                if (children[c].contains(d)) { int tmp = c; c = d; d = tmp; }

                int x1, x2, x3;
                if (children[a].contains(c)) {
                    x1 = subXor[c];
                    x2 = subXor[a] ^ subXor[c];
                    x3 = totalXor ^ subXor[a];
                } else if (children[c].contains(a)) {
                    x1 = subXor[a];
                    x2 = subXor[c] ^ subXor[a];
                    x3 = totalXor ^ subXor[c];
                } else {
                    x1 = subXor[a];
                    x2 = subXor[c];
                    x3 = totalXor ^ subXor[a] ^ subXor[c];
                }
                int mx = Math.max(x1, Math.max(x2, x3));
                int mn = Math.min(x1, Math.min(x2, x3));
                ans = Math.min(ans, mx - mn);
            }
        }
        return ans;
    }

    private int dfs(int u, int p, List<Integer>[] graph, int[] subXor, Set<Integer>[] children) {
        for (int v : graph[u]) {
            if (v == p) continue;
            dfs(v, u, graph, subXor, children);
            subXor[u] ^= subXor[v];
            children[u].addAll(children[v]);
        }
        return subXor[u];
    }
}
