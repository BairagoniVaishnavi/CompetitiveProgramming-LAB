import java.util.*;

public class SubsetSumBacktracking {

    static boolean subsetSum(int[] arr, int index, int currentSum, int target) {
        // If target is achieved
        if (currentSum == target)
            return true;

        // If sum exceeds target or no elements left
        if (index == arr.length || currentSum > target)
            return false;

        // Include current element
        if (subsetSum(arr, index + 1, currentSum + arr[index], target))
            return true;

        // Exclude current element
        return subsetSum(arr, index + 1, currentSum, target);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        boolean result = subsetSum(arr, 0, 0, target);

        System.out.println(result ? "True" : "False");
    }
}
