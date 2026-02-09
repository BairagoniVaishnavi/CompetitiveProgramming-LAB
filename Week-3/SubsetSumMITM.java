import java.io.*;
import java.util.*;

public class SubsetSumMITM {

    // Generate all subset sums of given array
    static void generateSums(int[] arr, int idx, long sum, List<Long> list) {
        if (idx == arr.length) {
            list.add(sum);
            return;
        }
        // Exclude current element
        generateSums(arr, idx + 1, sum, list);
        // Include current element
        generateSums(arr, idx + 1, sum + arr[idx], list);
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

            // Split array into two halves
            int mid = N / 2;
            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, N);

            // Generate subset sums
            List<Long> leftSums = new ArrayList<>();
            List<Long> rightSums = new ArrayList<>();

            generateSums(left, 0, 0, leftSums);
            generateSums(right, 0, 0, rightSums);

            // Sort right half sums
            Collections.sort(rightSums);

            boolean found = false;

            // For each left sum, check if (S - leftSum) exists in right sums
            for (long lSum : leftSums) {
                long needed = S - lSum;
                if (Collections.binarySearch(rightSums, needed) >= 0) {
                    found = true;
                    break;
                }
            }

            output.append(found ? "YES" : "NO").append('\n');
        }

        System.out.print(output.toString());
    }

    // Fast input reader
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

