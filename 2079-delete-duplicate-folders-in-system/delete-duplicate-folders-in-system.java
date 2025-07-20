class Solution {
    class Node {
        Map<String, Node> children = new HashMap<>();
        boolean deleted = false;
        String serial = "";
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Node root = new Node();
        for (List<String> path : paths) {
            Node cur = root;
            for (String folder : path) {
                cur = cur.children.computeIfAbsent(folder, k -> new Node());
            }
        }

        Map<String, Integer> freq = new HashMap<>();
        serialize(root, freq);

        List<List<String>> ans = new ArrayList<>();
        dfsCollect(root, new ArrayList<>(), freq, ans);
        return ans;
    }

    private String serialize(Node node, Map<String, Integer> freq) {
        if (node.children.isEmpty()) {
            node.serial = "";
        } else {
            List<String> subs = new ArrayList<>();
            for (Map.Entry<String, Node> e : node.children.entrySet()) {
                String subSerial = serialize(e.getValue(), freq);
                subs.add(e.getKey() + "(" + subSerial + ")");
            }
            Collections.sort(subs);
            node.serial = String.join("", subs);
            freq.put(node.serial, freq.getOrDefault(node.serial, 0) + 1);
        }
        return node.serial;
    }

    private void dfsCollect(Node node, List<String> curr, Map<String, Integer> freq,
                            List<List<String>> ans) {
        if (!curr.isEmpty() && freq.getOrDefault(node.serial, 0) > 1) {
            return;  // skip this subtree
        }
        if (!curr.isEmpty()) {
            ans.add(new ArrayList<>(curr));
        }
        for (Map.Entry<String, Node> e : node.children.entrySet()) {
            curr.add(e.getKey());
            dfsCollect(e.getValue(), curr, freq, ans);
            curr.remove(curr.size() - 1);
        }
    }
}
