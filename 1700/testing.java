public class testing {
    public static void main(String[] args) {
        int ans1 = numberOfPaths(6,6);
        int ans2 = numberOfPaths1(6,6);

        System.out.println(ans1);
        System.out.println(ans2);
    }

    //alag hi behn ki lodi harkate

    static int numberOfPaths(int m, int n)
    {
        // Create a 2D table to store results of subproblems
        int[][] count = new int[m][n];

       count[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                count[i][j] = (i > 0 ? count[i - 1][j] : 0) + (j > 0 ? count[i][j - 1] : 0); //+ count
                // [i-1][j-1];
            }
        }
        return count[m - 1][n - 1];
    }

    static int numberOfPaths1(int m, int n)
    {
        // If either given row number is first or given column
        // number is first
        if (m == 1 || n == 1)
            return 1;

        // If diagonal movements are allowed then the last
        // addition is required.
        return numberOfPaths1(m - 1, n) + numberOfPaths1(m, n - 1);
        // + numberOfPaths(m-1, n-1);
    }

}
