import java.io.*;
import java.util.*;

public class ClosestSubsetSumMITM{

    static void generateSubsetSums(int[] arr, int idx, long sum, List<Long> list) {
        if (idx == arr.length) {
            list.add(sum);
            return;
        }
        // Exclude current element
        generateSubsetSums(arr, idx + 1, sum, list);
        // Include current element
        generateSubsetSums(arr, idx + 1, sum + arr[idx], list);
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        StringBuilder output = new StringBuilder();

        int T = fs.nextInt();

        while (T-- > 0) {
            int N = fs.nextInt();
            long S = fs.nextLong();

            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = fs.nextInt();
            }

            // Split array
            int mid = N / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, N);

            // Generate subset sums
            List<Long> leftSums = new ArrayList<>();
            List<Long> rightSums = new ArrayList<>();

            generateSubsetSums(left, 0, 0, leftSums);
            generateSubsetSums(right, 0, 0, rightSums);

            // Sort right sums
            Collections.sort(rightSums);

            long ans = Long.MAX_VALUE;

            // For each sum in left, binary search best match in right
            for (long lSum : leftSums) {
                long target = S - lSum;

                int idx = Collections.binarySearch(rightSums, target);

                if (idx >= 0) {
                    ans = 0;
                    break;
                } else {
                    idx = -idx - 1;

                    if (idx < rightSums.size()) {
                        ans = Math.min(ans, Math.abs(lSum + rightSums.get(idx) - S));
                    }
                    if (idx - 1 >= 0) {
                        ans = Math.min(ans, Math.abs(lSum + rightSums.get(idx - 1) - S));
                    }
                }
            }

            output.append(ans).append('\n');
        }

        System.out.print(output.toString());
    }

    // Fast Input Reader
    static class FastScanner {
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        private final InputStream in = System.in;

        private int readByte() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c, sign = 1, val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        long nextLong() throws IOException {
            int c, sign = 1;
            long val = 0;
            do {
                c = readByte();
            } while (c <= ' ');

            if (c == '-') {
                sign = -1;
                c = readByte();
            }

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }
    }
}
