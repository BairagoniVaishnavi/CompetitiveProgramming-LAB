
import java.util.*;

public class Week1_Assign2 {

    // Find first occurrence
    static int firstOcc(int[] arr, int n, int x) {
        int low = 0, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == x) {
                ans = mid;
                high = mid - 1;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    // Find last occurrence
    static int lastOcc(int[] arr, int n, int x) {
        int low = 0, high = n - 1, ans = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == x) {
                ans = mid;
                low = mid + 1;
            } else if (arr[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            int Q = sc.nextInt();

            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            for (int i = 0; i < Q; i++) {
                int x = sc.nextInt();
                int first = firstOcc(arr, N, x);
                int last = lastOcc(arr, N, x);

                int count = (first == -1) ? 0 : (last - first + 1);
                System.out.println(count); // NO SPACE → prints 230
            }
        }

        sc.close();
    }
}
