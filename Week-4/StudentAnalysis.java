import java.util.*;

class FenwickTree {
    int[] tree;
    int n;

    FenwickTree(int n) {
        this.n = n;
        tree = new int[n + 1];   // 1-based indexing
    }

    // Add delta at index
    void update(int index, int delta) {
        while (index <= n) {
            tree[index] += delta;
            index += index & (-index);
        }
    }

    // Prefix sum from 1 to index
    int query(int index) {
        int sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index & (-index);
        }
        return sum;
    }
}

public class StudentAnalysis {

    public static void main(String[] args) {

        /* =========================
           TASK 1: Student Attendance
           ========================= */

        System.out.println("---- Student Attendance Analysis ----");

        int[] attendance = {1, 2, 1, 2, 1};
        int n1 = attendance.length;

        FenwickTree attendanceFT = new FenwickTree(n1);

        // Build Fenwick Tree
        for (int i = 0; i < n1; i++) {
            attendanceFT.update(i + 1, attendance[i]);
        }

        // Query: total till Day 4
        System.out.println("Total attendance till Day 4 = "
                + attendanceFT.query(4));

        // Update Day 3 from 1 → 2
        int oldAttendance = attendance[2];
        int newAttendance = 2;
        int delta1 = newAttendance - oldAttendance;

        attendanceFT.update(3, delta1);
        attendance[2] = newAttendance;

        System.out.println("After update, total attendance till Day 4 = "
                + attendanceFT.query(4));


        /* =========================
           TASK 2: Daily Sales Revenue
           ========================= */

        System.out.println("\n---- Daily Sales Revenue Analysis ----");

        int[] revenue = {200, 150, 300, 250, 100, 180, 220};
        int n2 = revenue.length;

        FenwickTree revenueFT = new FenwickTree(n2);

        // Build Fenwick Tree
        for (int i = 0; i < n2; i++) {
            revenueFT.update(i + 1, revenue[i]);
        }

        // Query: total till Day 6
        System.out.println("Total sales revenue till Day 6 = "
                + revenueFT.query(6));

        // Update Day 5 from 100 → 160
        int oldRevenue = revenue[4];
        int newRevenue = 160;
        int delta2 = newRevenue - oldRevenue;

        revenueFT.update(5, delta2);
        revenue[4] = newRevenue;

        System.out.println("After update, total sales revenue till Day 6 = "
                + revenueFT.query(6));
    }
}
