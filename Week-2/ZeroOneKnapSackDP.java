import java.util.*;

public class ZeroOneKnapSackDP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int W = sc.nextInt();

        int[] val = new int[n];
        int[] wt = new int[n];

        for (int i = 0; i < n; i++) {
            val[i] = sc.nextInt();
            wt[i] = sc.nextInt();
        }

        int[][] dp = new int[n + 1][W + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (wt[i - 1] <= w) {
                    dp[i][w] = Math.max(
                        val[i - 1] + dp[i - 1][w - wt[i - 1]],
                        dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        System.out.println(dp[n][W]);
    }
}
