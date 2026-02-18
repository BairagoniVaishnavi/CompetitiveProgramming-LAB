import java.util.*;

class SegmentTree {

    int[] minTree;
    int[] maxTree;
    int n;

    SegmentTree(int[] arr) {
        n = arr.length;
        minTree = new int[4 * n];
        maxTree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    // Build tree
    void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            minTree[node] = arr[start];
            maxTree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);

            minTree[node] = Math.min(minTree[2 * node + 1], minTree[2 * node + 2]);
            maxTree[node] = Math.max(maxTree[2 * node + 1], maxTree[2 * node + 2]);
        }
    }

    // Range Min Query
    int rangeMin(int node, int start, int end, int l, int r) {

        if (r < start || end < l)
            return Integer.MAX_VALUE;

        if (l <= start && end <= r)
            return minTree[node];

        int mid = (start + end) / 2;

        int left = rangeMin(2 * node + 1, start, mid, l, r);
        int right = rangeMin(2 * node + 2, mid + 1, end, l, r);

        return Math.min(left, right);
    }

    // Range Max Query
    int rangeMax(int node, int start, int end, int l, int r) {

        if (r < start || end < l)
            return Integer.MIN_VALUE;

        if (l <= start && end <= r)
            return maxTree[node];

        int mid = (start + end) / 2;

        int left = rangeMax(2 * node + 1, start, mid, l, r);
        int right = rangeMax(2 * node + 2, mid + 1, end, l, r);

        return Math.max(left, right);
    }

    // Update
    void update(int node, int start, int end, int index, int value) {

        if (start == end) {
            minTree[node] = value;
            maxTree[node] = value;
        } else {
            int mid = (start + end) / 2;

            if (index <= mid)
                update(2 * node + 1, start, mid, index, value);
            else
                update(2 * node + 2, mid + 1, end, index, value);

            minTree[node] = Math.min(minTree[2 * node + 1], minTree[2 * node + 2]);
            maxTree[node] = Math.max(maxTree[2 * node + 1], maxTree[2 * node + 2]);
        }
    }
}

public class Main {

    public static void main(String[] args) {

        int[] readings = {32, 28, 30, 35, 29, 31, 34, 33};

        SegmentTree st = new SegmentTree(readings);

        // RangeMin(2,6)
        System.out.println(st.rangeMin(0, 0, readings.length - 1, 2, 6));

        // RangeMax(1,5)
        System.out.println(st.rangeMax(0, 0, readings.length - 1, 1, 5));

        // Update(3,27)
        st.update(0, 0, readings.length - 1, 3, 27);

        // RangeMin(2,6) again
        System.out.println(st.rangeMin(0, 0, readings.length - 1, 2, 6));
    }
}
