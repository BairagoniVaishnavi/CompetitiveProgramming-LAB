import java.util.*;

public class JobSequencing {

    static class Job {
        int deadline;
        int profit;

        Job(int d, int p) {
            deadline = d;
            profit = p;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while (T-- > 0) {
            int N = sc.nextInt();
            Job[] jobs = new Job[N];
            int maxDeadline = 0;

            for (int i = 0; i < N; i++) {
                int d = sc.nextInt();
                int p = sc.nextInt();
                jobs[i] = new Job(d, p);
                maxDeadline = Math.max(maxDeadline, d);
            }

            // Sort jobs by profit (descending)
            Arrays.sort(jobs, (a, b) -> Integer.compare(b.profit, a.profit));

            boolean[] slot = new boolean[maxDeadline + 1];

            int jobsDone = 0;
            long totalProfit = 0;

            for (Job job : jobs) {
                // Find a free slot before or on deadline
                for (int t = job.deadline; t >= 1; t--) {
                    if (!slot[t]) {
                        slot[t] = true;
                        jobsDone++;
                        totalProfit += job.profit;
                        break;
                    }
                }
            }

            System.out.println(jobsDone + " " + totalProfit);
        }

        sc.close();
    }
}

