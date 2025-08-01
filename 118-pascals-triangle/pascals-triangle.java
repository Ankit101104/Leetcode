import java.util.*;

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();

            for (int j = 0; j <= i; j++) {
                // First and last elements are always 1
                if (j == 0 || j == i) {
                    row.add(1);
                } else {
                    // Sum of the two elements above
                    int val = triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j);
                    row.add(val);
                }
            }

            triangle.add(row);
        }

        return triangle;
    }

    // For testing
    public static void main(String[] args) {
        Solution sol = new Solution();
        int numRows = 5;
        List<List<Integer>> result = sol.generate(numRows);

        for (List<Integer> row : result) {
            System.out.println(row);
        }
    }
}
