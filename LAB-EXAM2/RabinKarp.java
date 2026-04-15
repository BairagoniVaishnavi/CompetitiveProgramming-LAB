public class RabinKarp {
    static final int d = 10;

    public static void main(String[] args) {
        String text = "ABABABA";
        String pattern = "ABA";

        search(text, pattern);
    }

    static void search(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();

        int patternHash = 0, textHash = 0;
        int h = 1;

        // A=1, B=2
        for (int i = 0; i < m; i++) {
            patternHash = patternHash * d + (pattern.charAt(i) - 'A' + 1);
            textHash = textHash * d + (text.charAt(i) - 'A' + 1);
        }

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash) {
                if (text.substring(i, i + m).equals(pattern))
                    System.out.println("Match at index: " + i);
            }

            if (i < n - m) {
                textHash = (textHash - (text.charAt(i) - 'A' + 1) * (int)Math.pow(d, m - 1)) * d
                        + (text.charAt(i + m) - 'A' + 1);
            }
        }
    }
}