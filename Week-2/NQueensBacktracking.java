import java.util.*;

public class NQueensBacktracking {

    static int N;
    static char[][] board;

    // Check if queen can be placed at board[row][col]
    static boolean isSafe(int row, int col) {

        // Check same column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q')
                return false;
        }

        // Check upper-left diagonal
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }

        // Check upper-right diagonal
        for (int i = row - 1, j = col + 1; i >= 0 && j < N; i--, j++) {
            if (board[i][j] == 'Q')
                return false;
        }

        return true;
    }

    // Backtracking function
    static boolean solve(int row) {
        if (row == N)
            return true;

        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 'Q';

                if (solve(row + 1))
                    return true;

                // Backtrack
                board[row][col] = '.';
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        board = new char[N][N];

        // Initialize board
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], '.');
        }

        solve(0);

        // Print one valid solution
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
