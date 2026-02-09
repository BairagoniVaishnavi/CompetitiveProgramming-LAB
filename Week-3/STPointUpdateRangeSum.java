import java.util.*;

public class STPointUpdateRangeSum {

    static long[] segTree;
    static int N;

    // Build segment tree
    static void build(int[] arr, int idx, int left, int right) {
        if (left == right) {
            segTree[idx] = arr[left];
            return;
        }
        int mid = (left + right) / 2;
        build(arr, 2 * idx + 1, left, mid);
        build(arr, 2 * idx + 2, mid + 1, right);
        segTree[idx] = segTree[2 * idx + 1] + segTree[2 * idx + 2];
    }

    // Point update: update position pos with value val
    static void update(int idx, int left, int right, int pos, int val) {
        if (left == right) {
            segTree[idx] = val;
            return;
        }
        int mid = (left + right) / 2;
        if (pos <= mid) {
            update(2 * idx + 1, left, mid, pos, val);
        } else {
            update(2 * idx + 2, mid + 1, right, pos, val);
        }
        segTree[idx] = segTree[2 * idx + 1] + segTree[2 * idx + 2];
    }

    // Range sum query
    static long query(int idx, int left, int right, int ql, int qr) {
        // No overlap
        if (qr < left || ql > right)
            return 0;

        // Complete overlap
        if (ql <= left && right <= qr)
            return segTree[idx];

        // Partial overlap
        int mid = (left + right) / 2;
        return query(2 * idx + 1, left, mid, ql, qr)
             + query(2 * idx + 2, mid + 1, right, ql, qr);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            N = sc.nextInt();
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            segTree = new long[4 * N];
            build(arr, 0, 0, N - 1);

            int Q = sc.nextInt();

            while (Q-- > 0) {
                int type = sc.nextInt();

                if (type == 1) {
                    // Update
                    int index = sc.nextInt();
                    int value = sc.nextInt();
                    update(0, 0, N - 1, index, value);
                } else if (type == 2) {
                    // Range sum query
                    int L = sc.nextInt();
                    int R = sc.nextInt();
                    System.out.println(query(0, 0, N - 1, L, R));
                }
            }
        }
    }
}
