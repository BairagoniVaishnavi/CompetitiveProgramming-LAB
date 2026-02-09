
import java.util.*;

public class  MaximumProfitStreakDivideandConquer{

    // Function to find maximum crossing subarray sum
    private static long maxCrossingSum(long[] arr, int left, int mid, int right) {
        long sum = 0;
        long leftSum = Long.MIN_VALUE;

        for (int i = mid; i >= left; i--) {
            sum += arr[i];
            leftSum = Math.max(leftSum, sum);
        }

        sum = 0;
        long rightSum = Long.MIN_VALUE;

        for (int i = mid + 1; i <= right; i++) {
            sum += arr[i];
            rightSum = Math.max(rightSum, sum);
        }

        return leftSum + rightSum;
    }

    // Divide and Conquer function
    private static long maxSubArraySum(long[] arr, int left, int right) {
        if (left == right) {
            return arr[left];
        }

        int mid = left + (right - left) / 2;

        long leftMax = maxSubArraySum(arr, left, mid);
        long rightMax = maxSubArraySum(arr, mid + 1, right);
        long crossMax = maxCrossingSum(arr, left, mid, right);

        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            long[] arr = new long[N];

            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextLong();
            }

            long result = maxSubArraySum(arr, 0, N - 1);
            System.out.println(result);
        }

        sc.close();
    }
}
