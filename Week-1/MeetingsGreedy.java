import java.util.*;

public class MeetingsGreedy {

    static class Meeting {
        int start, end;
        Meeting(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            Meeting[] meetings = new Meeting[N];

            for (int i = 0; i < N; i++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                meetings[i] = new Meeting(s, e);
            }

            // Sort by end time
            Arrays.sort(meetings, (a, b) -> Integer.compare(a.end, b.end));

            int count = 1; // attend first meeting
            int lastEnd = meetings[0].end;

            for (int i = 1; i < N; i++) {
                if (meetings[i].start > lastEnd) { // STRICT condition
                    count++;
                    lastEnd = meetings[i].end;
                }
            }

            System.out.println(count);
        }

        sc.close();
    }
}

