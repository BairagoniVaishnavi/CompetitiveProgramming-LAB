import java.util.*;

public class FibonacciDP {
    static long[] dp;

    static long fib(int n) {
        if (n <= 1) return n;
        if (dp[n] != -1) return dp[n];
        return dp[n] = fib(n - 1) + fib(n - 2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        dp = new long[n + 1];
        Arrays.fill(dp, -1);

        System.out.println(fib(n));
    }
}
