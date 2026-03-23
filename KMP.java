public class KMP {

    // Function to compute LPS array
    static void computeLPS(String pattern, int[] lps) {
        int len = 0;
        int i = 1;

        lps[0] = 0; // first is always 0

        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1]; // jump back
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    // KMP Search
    static void search(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        int[] lps = new int[m];

        computeLPS(pattern, lps);

        int i = 0; // text index
        int j = 0; // pattern index

        while (i < n) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1];
            }

            else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";

        search(text, pattern);
    }
}